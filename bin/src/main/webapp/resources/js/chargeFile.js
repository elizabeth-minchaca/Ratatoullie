$(document).ready(function () {
    //Cargar Imagen
    $("#imagePreview").on("click", function () {
        $("#uploadFile").click();
    });
    $("#uploadFile").on("change", function () {
        var files = !!this.files ? this.files : [];
        if (!files.length || !window.FileReader)
            return; // no file selected, or no FileReader support
        if (/^image/.test(files[0].type)) { // only image file
            var reader = new FileReader(); // instance of the FileReader
            reader.readAsDataURL(files[0]); // read the local file
            reader.onloadend = function () { // set image data as background of div
                $("#imagePreview").html("");
                $("#imagePreview").css("background-image", "url(" + this.result + ")");
            };
        }
    });
    
    //Cargar Imagen
    $("#imageEditPreview").on("click", function () {
        $("#uploadFile").click();
    });


    $("#uploadFile").on("change", function () {
        var files = !!this.files ? this.files : [];
        if (!files.length || !window.FileReader)
            return; // no file selected, or no FileReader support

        if (/^image/.test(files[0].type)) { // only image file
            var reader = new FileReader(); // instance of the FileReader
            reader.readAsDataURL(files[0]); // read the local file

            reader.onloadend = function () { // set image data as background of div
                $("#imageEditPreview").html("");
                $("#imageEditPreview").css("background-image", "url(" + this.result + ")");
            };
        }
    });

    $("#imagePreviewMenu").on("click", function () {
        $("#uploadFileMenu").click();
    });
    $("#uploadFileMenu").on("change", function () {
        var files = !!this.files ? this.files : [];
        if (!files.length || !window.FileReader)
            return; // no file selected, or no FileReader support
        if (/^image/.test(files[0].type)) { // only image file
            var reader = new FileReader(); // instance of the FileReader
            reader.readAsDataURL(files[0]); // read the local file
            reader.onloadend = function () { // set image data as background of div
                $("#imagePreviewMenu").html("");
                $("#imagePreviewMenu").css("background-image", "url(" + this.result + ")");
            };
        }
    });

    
});
