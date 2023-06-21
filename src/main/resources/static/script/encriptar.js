
const textarea = document.getElementById("caja2");
let boton0 = document.getElementById("boton1");
let boton_1 = document.getElementById("boton2");
let boton_2 = document.getElementById("boton3");
const errorMessage = document.getElementById('error-message');

input.addEventListener('input', () => {
  
});

boton0.addEventListener("click", function() {


});
boton_1.addEventListener("click", function(){

    
      
});

boton_2.addEventListener("click", function(){
  /*Utilice este metodo pór que es compatible con la mayoria de navegadores y tambien
  Porque al usar document.execCommand("") no me funcionaba en mi navegador y
  el visual code me subrayaba la palabra "execCommand" indicando que habia algo
  malo xd*/
  navigator.clipboard.writeText(textarea.value);
  
});
