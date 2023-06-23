document.addEventListener("DOMContentLoaded", function() {
	let botonMensaje2 = document.getElementById("botonMsm2");
	const textarea = document.getElementById("cajaMensaje");
	
	botonMensaje2.addEventListener("click", function() {
    navigator.clipboard.writeText(textarea.value);
  });

	
});