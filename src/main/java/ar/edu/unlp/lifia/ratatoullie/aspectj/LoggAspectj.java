package ar.edu.unlp.lifia.ratatoullie.aspectj;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggAspectj {
	private ArrayList<String> methodNoFinalParams;
	public ArrayList<String> getMethodNoFinalParams() {
		return methodNoFinalParams;
	}
	public void setMethodNoFinalParams(ArrayList<String> methodNoFinalParams) {
		this.methodNoFinalParams = methodNoFinalParams;
	}
	public LoggAspectj() {
		setMethodNoFinalParams(new ArrayList<String>());
		getMethodNoFinalParams().add("login");
	}	
	@Before("execution(* ar.edu.unlp.lifia.ratatoullie.service.impl..*.*(..)))")
	public void logBefore(JoinPoint joinPoint) {		
		Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
		Object[] parameters = joinPoint.getArgs();
		String[] parametersNames = methodParameters(joinPoint.getTarget(), joinPoint.getSignature().getName());
		String params=" ";
		for (int i = 0; i < parametersNames.length; i++) {
			if(!parametersNames[i].toLowerCase().contains("pass")){
				params+=" "+parameters[i];
			}
		}
		logger.info("Running: "+joinPoint.getSignature().getName()+ params);		
	}
	@After("execution(* ar.edu.unlp.lifia.ratatoullie.service.impl..*.*(..)))")
	public void logAfter(JoinPoint joinPoint) {		
		Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
		logger.info("Finalized: "+joinPoint.getSignature().getName());

	}
	@AfterThrowing(pointcut = "execution(* ar.edu.unlp.lifia.ratatoullie.service.impl..*.*(..)))",
			throwing= "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
		Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
		
		logger.error(joinPoint.getSignature().getName() +" error: "+error.getClass()+" "+error.getMessage());
	}	
	private String[] methodParameters(Object object, String method){
		 Method[] declaredMethods = object.getClass().getDeclaredMethods();
		 ArrayList<String> parameters= new ArrayList<String>();
	        for (Method declaredMethod : declaredMethods) {
	            if (declaredMethod.getName().equals(method)) {
	                for (Parameter parameter: declaredMethod.getParameters()) {
	                	parameters.add(parameter.getName());
					}
	                return parameters.toArray(new String[parameters.size()]);
	            }
	        }
		return null;
	}
}