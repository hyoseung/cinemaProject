/**
 * 
 */
function select_cinema(num){
	var name = document.getElementById(num.getAttribute('id')).getAttribute('id');
	
	$.ajax({
		url:"cinemaInfo.sku",
		data:{
			name:name
		},
		type:"post",
		success:
			function(image){
			var str=image;
			var index = str.split('?');
			var mainimg=""; var address=""; var phone=""; var imageLocation="";
			mainimg+="<center><img id='mainimg' src='"+index[0]+"'/></center>";
			document.getElementById("mainimg").innerHTML = mainimg;
			address+=index[1];
			document.getElementById("address").innerHTML = address;
			phone+=index[2];
			document.getElementById("phone").innerHTML = phone;
			imageLocation+="<center><img class='cinema-image' src='"+index[3]+"'/></center>";
			document.getElementById("imageLocation").innerHTML = imageLocation;
		}
	})
}