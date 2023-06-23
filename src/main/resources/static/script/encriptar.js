document.addEventListener("DOMContentLoaded", function() {
  let boton_2 = document.getElementById("boton3");
  let botonMensaje = document.getElementById("botonMsm");
  
  
  boton_2.addEventListener("click", function() {
    var resultValue = document.getElementById("caja4").value; 
    var valueToCopy = resultValue.substring(5);
    navigator.clipboard.writeText(valueToCopy);
  });
  
  botonMensaje.addEventListener("click", function() {
    var resultValue = document.getElementById("cajaMsn").value; 
    var valueToCopy = resultValue.substring(9);
    navigator.clipboard.writeText(valueToCopy);
  });
  
  
});
