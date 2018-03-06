package ar.edu.unlp.lifia.ratatoullie.init;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ar.edu.unlp.lifia.ratatoullie.exception.ImpossibleToCreateEntityException;
import ar.edu.unlp.lifia.ratatoullie.service.CommentService;
import ar.edu.unlp.lifia.ratatoullie.service.RestaurantService;
import ar.edu.unlp.lifia.ratatoullie.service.UserService;
import ar.edu.unlp.lifia.ratatoullie.service.impl.InitContexService;


@Component
public class Init {
	@Autowired
	private InitContexService initContexService;	
	public InitContexService getInitContexService() {
		return initContexService;
	}
	public void setInitContexService(InitContexService initContexService) {
		this.initContexService = initContexService;
	}
	@PostConstruct
	public  void init() throws FileNotFoundException, SQLException{
		getInitContexService().initRaratoullie();
		initDatabase();
	}
	@Autowired
	UserService userService;
	@Autowired
	RestaurantService restaurantService;
	@Autowired
	CommentService commentService;
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public RestaurantService getRestaurantService() {
		return restaurantService;
	}
	public void setRestaurantService(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}
	public CommentService getCommentService() {
		return commentService;
	}
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	@Value("${workspace}")
	private String workspace;

	public String getWorkspace() {
		return workspace;
	}
	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}
	public void initDatabase(){
		double latitudeBaseUser=-34.8927459785957;
		double longitudeBaseUser=-57.9566488286946;
		double decrement=0.0027;
		double latitudeBaseUserResponsible=-34.92354742874274;
		double longitudeBaseUserResponsible=-57.989783764933236;
		String[] restaurantNames={"Mariscos La Tasca de Paco","Las Ranas","Osornio Restaurante & Cafe","Restaurante Club Tacuari","Club Atenas Restaurant","Resto Urquiza","Sexto Brasserie",
				"Me Piace Confiteria","La Madonnina","Las Lilas","Dionisio Vinoteca Restaurante","Foodie SpecialBurger","Akari Sushi Bar","Masse Boulangerie",
				"A Rienda Suelta","La Trattoria","Los Novillos","Lupita","Mezzogiorno","La Parolaccia del Mare"};
		String[] restaurantImages={"restaurant.jpg","restaurant2.jpg","restaurant3.jpg","restaurant4.jpg","restaurant5.jpg","restaurant6.jpg",
				"restaurant7.jpg","restaurant8.jpg","restaurant9.jpg","restaurant10.jpg","restaurant11.jpg","restaurant12.jpg","restaurant13.jpg",
				"restaurant14.jpg","restaurant15.jpg","restaurant16.jpg","restaurant17.jpg","restaurant18.jpg","restaurant19.jpg","restaurant20.jpg"};
		String[] menuName={"Trucha marinada con salsa de albahaca ","Judias verdes","Berenjenas agridulces con hamburguesa de ternera a la plancha",
				"Sopa de huevo rápida y currywurst","Bonito marinado al curry con calabacín a la plancha","Fajitas de ternera Tex Mex con ensalada de patata irlandesa",
				"Tallarines de calabacín con carne","Salteado de ternera con brócoli","Arroz con picadillo habanero","Arroz picante mexicano con chuleta de cerdo a la plancha","Curry de pollo con zanahorias",
				"Para los que buscan algo ligero y sin complicaciones, este curry rapidísimo de pechuga de pollo y zanahorias ",
				"Pimientos rellenos de carne y queso con ensalada","Curry de calabaza y espinacas con calamares a la plancha",
				"Patatas mimosas","Coliflor con queso y pimientos del piquillo con filete de pescado a la plancha","Pasta rellena de carne",
				"Calamares a la romana sin huevo con ensalada","Salmón al horno","Costillas asadas rápidas con ensalada de tomate"};
		String[] menuDescrition={"Tan solo hay que acordarse de dejar el pescado en la marinada antes de salir de casa y cuando sea hora de comer, preparar la comida sera visto y no visto, tanto la trucha marinada con salsa como los champiñones al ajillo.",
				"Judias verdes con tomate y jamon y pechuga de pollo a la plancha",
				"Estas berenjenas chinas agridulces que podemos servir como guarnición de un hamburguesa de ternera casera a la plancha",
				"Que el día se presenta frío y te mueres por algo caliente al llegar a casa… si es así, esta sopa de huevo rápida está lista en menos de 5 minutos y es un primer plato fabuloso para acompañar a un segundo también rapidísimo como este currywurst alemán.",
				"Delicioso, este bonito marinado al curry con verduras a la plancha es un menú rápido y ligero",
				"Estas fajitas de ternera se preparan en un santiamén y quedan genial con una rapidísima ensalada irlandesa de patatas.",
				"Los tallarines de calabacín son una alternativa muy interesante a la pasta ya que tienen muchísimas menos calorías y son muy recomendables si estamos siguiendo una dieta baja en carbohidratos",
				"Un filete a la plancha con unas verduras cocidas ",
				"El arroz blanco, incluso el integral, es una guarnición que puede prepararse con antelación y conservarse varios días en la nevera lo que nos ahorra mucho tiempo y nos permite disfrutar de menús como este picadillo habanero ",
				"Este arroz picante mexicano acompañado con una chuleta de cerdo a la plancha.",
				"Para los que buscan algo ligero y sin complicaciones,curry de pechuga de pollo y zanahorias ",
				"Arroz frito cantonés de primero y una merluza en escabeche de segundo",
				"Estos pimientos rellenos de carne y queso,son perfectos para cuando intentamos seguir una dieta baja en carbohidratos simples. ",
				"Curry de calabaza y espinacas que acompañado con unos chipirones a la plancha.",
				"Patatas mimosas, algo parecido a unas patatas a la carbonara",
				"Un simple filete de pescado a la plancha, con la compañía de esta coliflor con queso y pimientos del piquillo.",
				"Pasta rellena de carne con una salsa de tomatitos cherry",
				"Un clásico que gusta a todos, los calamares a la romana con una buena ensalada",
				"Salmón al horno con bacon y cebolla y patatas",
				"Costillas de cerdo de chuparse los dedos,con ensalada de tomates",};
		String[] menuImage={"trucha-marinada-00-680x461.jpg","judias-tomate-00-680x453.jpg","berenjenas-agridulces-00-680x453.jpg","sopa-de-huevo-00.jpg","bonito-marinado-curry-04-680x453.jpg",
				"fajitas-ternera-00-680x453.jpg","tallarines-calabacin-05-680x453.jpg","ternera-brocoli-0-680x453.jpg","picadillo-habanero-00-680x453.jpg","arroz-picante-00-680x453.jpg",
				"curry-pollo-zanahoria-00-680x453.jpg","IMG_8468-MERLUZA-EN-ESCABECHE-AL-MICROONDAS-0.jpg","pimientos-rellenos-carne-00-680x453.jpg","curry-calabaza-00-680x453.jpg",
				"patatas-mimosa-9.jpg","coliflor-queso-pimientos-00-680x422.jpg","Pasta-rellena-de-carne-10.jpg","calamares-romana-00.jpg","salmon-horno-03.jpg","costillas-horno-rapidas-00.jpg"};
		String[] userName={"Pedro","Juan","Rebeca","Debora","Nancy","Lucia","Susana","Dora","Teresa","Franco","Francisca","Olga","Maria",
				"Alejandra","Valentina","Laura","Silvia","Manuel","Joaquin","Matias"};
		String[] userResponsibleName={"Luciano","Daniel","Emanuel","Ciro","Carlos","Pedro","Luis","Nicolas","Silvina","Florencia",
				"Ana","Marisa","Angelica","Damaris","Jorge","Renzo","Ruben","Miguel","Jose","Juan"};
		String[] userLastName={"Lopez","Gomez","Alvarez","Martinez","Luna","Garcia","Tinelli","Robledo","Martinon","Rodriguez","Flores",
				"Rampazzo","Bedel","Espindola","Senn","Barbero","Lasorsa","Aguilar","Pistoni","Gauchat"};
		String password=toMd5("password");
		for (int i = 0; i < 20; i++) {
			try {
				getUserService().createUser(userName[i], userLastName[i], "User"+i+"@mail.com", password, password, latitudeBaseUser-decrement*i, longitudeBaseUser);

			} catch (ImpossibleToCreateEntityException e) {
			}
		}

		decrement=0.0060;
		for (int i = 0; i < 10; i++) {
			try {			
				String imageRestaurant=getWorkspace()+"src/main/webapp/resources/imgs/"+restaurantImages[i];
				String imageMenu=getWorkspace()+"src/main/webapp/resources/imgs/"+menuImage[i];
				getUserService().createUserResponsible(userResponsibleName[i], userLastName[i], "UserResponsible"+i+"@mail.com", password, password,
						latitudeBaseUserResponsible, longitudeBaseUserResponsible+decrement*i, restaurantNames[i], new Date().getTime(), 
						latitudeBaseUserResponsible, longitudeBaseUserResponsible+decrement*i,multipartFile(imageRestaurant), menuName[i], menuDescrition[i],multipartFile(imageMenu));
			} catch (Exception e) {
			}
		}
		for (int k = 0; k < 20; k++) {
			for (int i = 0; i < 10; i++) {
				try {
					getCommentService().commentRestaurant("User"+k+"@mail.com", "Buen Restaurante ", new Date().getTime(), restaurantNames[i], latitudeBaseUserResponsible, longitudeBaseUserResponsible+decrement*i, "positive");
					getCommentService().commentMenu("User"+k+"@mail.com", "Exelente comida ", new Date().getTime(), restaurantNames[i], latitudeBaseUserResponsible, longitudeBaseUserResponsible+decrement*i, "positive");
				} catch (Exception e) {
				}
			}
		}
		latitudeBaseUserResponsible=-34.91819882954165;
		longitudeBaseUserResponsible=-57.98707151727285;
		decrement=0.006;
		for (int i = 10; i < 20; i++) {
			try {			
				String imageRestaurant=getWorkspace()+"src/main/webapp/resources/imgs/"+restaurantImages[i];
				String imageMenu=getWorkspace()+"src/main/webapp/resources/imgs/"+menuImage[i];
				getUserService().createUserResponsible(userResponsibleName[i], userLastName[i], "UserResponsible"+i+"@mail.com", password, password,
						latitudeBaseUserResponsible, longitudeBaseUserResponsible+decrement*(i-10), restaurantNames[i], new Date().getTime(), 
						latitudeBaseUserResponsible, longitudeBaseUserResponsible+decrement*(i-10),multipartFile(imageRestaurant), menuName[i], menuDescrition[i],multipartFile(imageMenu));
				getUserService().setUpNotification("UserResponsible"+i+"@mail.com", false, true, false);
			} catch (Exception e) {
			}
		}
		for (int k = 10; k < 20; k++) {
			for (int i = 10; i < 20; i++) {
				try {
					getCommentService().commentRestaurant("User"+k+"@mail.com", "Mal Restaurante", new Date().getTime(), restaurantNames[i], 
							latitudeBaseUserResponsible, longitudeBaseUserResponsible+decrement*(i-10), "negative");
					getCommentService().commentMenu("User"+k+"@mail.com", "Horrible comida", new Date().getTime(), restaurantNames[i], 
							latitudeBaseUserResponsible, longitudeBaseUserResponsible+decrement*(i-10), "negative");
				} catch (Exception e) {					
				}
			}
		}
		latitudeBaseUserResponsible=-34.93057045478348;
		longitudeBaseUserResponsible=-57.98353743710322;
		for (int i = 10; i < 20; i++) {
			try {			
				String imageRestaurant=getWorkspace()+"src/main/webapp/resources/imgs/"+restaurantImages[i];
				String imageMenu=getWorkspace()+"src/main/webapp/resources/imgs/"+menuImage[i];
				getRestaurantService().createRestaurant("UserResponsible"+i+"@mail.com", restaurantNames[i], new Date().getTime(), 
						latitudeBaseUserResponsible, longitudeBaseUserResponsible+decrement*(i-10),multipartFile(imageRestaurant),
						menuName[i], menuDescrition[i],multipartFile(imageMenu));
			} catch (Exception e) {
			}
		}
		for (int k = 10; k < 20; k++) {
			for (int i = 10; i < 20; i++) {
				try {
					getCommentService().commentRestaurant("User"+k+"@mail.com", "Interesante Restaurante ", new Date().getTime(), restaurantNames[i], 
							latitudeBaseUserResponsible, longitudeBaseUserResponsible+decrement*(i-10), "neutral");
				} catch (Exception e) {					
				}
			}
		}
		for (int k = 0; k < 20; k++) {
			for (int i = 10; i < 20; i++) {
				try {
					getCommentService().commentRestaurant("User"+k+"@mail.com", "Exelente Restaurante "+i, new Date().getTime(), restaurantNames[i], 
							latitudeBaseUserResponsible, longitudeBaseUserResponsible+decrement*(i-10), "positive");
				} catch (Exception e) {					
				}
			}
		}
		for (int i = 0; i < 10; i++) {
			for (int k = 0; k < 5; k++) {
				try {
					getUserService().addFollow("User"+i+"@mail.com", "UserResponsible"+k+"@mail.com");
					getUserService().addFollow("User"+i+"@mail.com", "User"+k+"@mail.com");
					getUserService().addFollow("UserResponsible"+i+"@mail.com", "User"+k+"@mail.com");
				} catch (Exception e) {
				}
			}
		}
		for (int i = 10; i < 20; i++) {
			for (int k = 5; k < 10; k++) {
				try{
					getUserService().addFollow("User"+i+"@mail.com", "UserResponsible"+k+"@mail.com");
					getUserService().addFollow("User"+i+"@mail.com", "User"+k+"@mail.com");
					getUserService().addFollow("UserResponsible"+i+"@mail.com", "User"+k+"@mail.com");
				} catch (Exception e) {
				}
			}
		}
		for (int i = 0; i < 10; i++) {
			try{
				getUserService().recommendFriends("User"+i+"@mail.com", i+1, "muy buena comida");
				getUserService().recommendFriends("UserResponsible"+i+"@mail.com", i+1, "horrible comida");
			} catch (Exception e) {
			}
		}
	}
	private MultipartFile multipartFile(String image) throws IOException {
		File file = new File(image);

		DiskFileItem diskFileItem= new DiskFileItem(file.getName(), Files.probeContentType(file.toPath()),
				true, file.getName(), (int) file.length(), file.getParentFile());
		try {
			InputStream input = new FileInputStream(file);
			OutputStream os = diskFileItem.getOutputStream();
			IOUtils.copy(input, os);
		} catch (Exception ex) {
		}
		return new CommonsMultipartFile(diskFileItem);

	}
	private String toMd5(String aString) {
		return DigestUtils.md5DigestAsHex(aString.getBytes());
	}
}