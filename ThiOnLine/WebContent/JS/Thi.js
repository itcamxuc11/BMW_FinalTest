var lq = document.getElementsByClassName("index-question");
var parent = document.getElementById("questions-complete");
for (i = 0; i < lq.length; i++) {
	lq[i].innerHTML = i + 1;
	lq[i].id = "c" + (i + 1);
	var tmp = document.createElement("a");
	tmp.className = "btn btn-outline-primary ml-1";
	tmp.innerHTML = i + 1;
	tmp.href = "#c" + (i + 1);
	parent.appendChild(tmp);
}

var txts = document.getElementById("second");
var txtm = document.getElementById("minute");
var s = txts.innerHTML;
var m = txtm.innerHTML;
var time = setInterval(function() {
	if (s == 0) {
		if (m == 0)
			document.getElementById("btn-accept-submit").click();
		else {
			s = 59;
			m--;
			txtm.innerHTML = m;
			txts.innerHTML = s;
		}
	} else {
		s--;
		txts.innerHTML = s;
	}
}, 1000)
$(document).ready(function() {
	$("#btn-accept-submit").click(function() {
		var baiThi = "";
		var luachon;
		var al = document.getElementsByClassName("answorten");
		for (i = 0; i < al.length; i++) {
			luachon = "*";
			var ch = al[i].querySelectorAll('input[type="radio"]')
			for (j = 0; j < ch.length; j++) {
				if (ch[j].checked == true)
					luachon = ch[j].getAttribute("data-choice");
			}
			baiThi += luachon;
		}
		post('KetQuaThi', {
			baiThi : baiThi
		});
	})
})

 $("input:radio").change(function(){
     var ma = $(this).attr('name');
     var el = document.querySelector('span[data-ma="'+ma+'"');
     var iid = el.id;
     var but = document.querySelector('a[href="#'+iid+'"');
     but.setAttribute("class","btn btn-primary ml-1");
 })