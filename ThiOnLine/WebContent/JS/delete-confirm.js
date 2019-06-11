var aElems = document.getElementsByClassName("delete-link");
for (var i = 0, len = aElems.length; i < len; i++) {
	aElems[i].onclick = function()
	{
		var check = confirm("Bạn có chắc chắn?");
		if (check == true){
			return true;
		}
		else return false
	}
}