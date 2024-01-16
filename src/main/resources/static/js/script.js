function getCapstone(id) {
	if (document.getElementById("capstone" + id).innerHTML == "") {
		fetch('http://localhost:8080/getCapstone/' + id) // use HomeController to fetch from our service
			.then(capstone => capstone.json()) // JSONify the data returned
			.then(function(capstone) { // with the JSON data
			
				var textToDisplay = ""; // create and append to a blank var
				
				textToDisplay += "Description: " + capstone.description + "<br>";
				textToDisplay += "Author: " + capstone.author + "<br>";
				textToDisplay += "Vote: " + capstone.vote + "<br>";
				
								
				//finally, change our relevant div to display the var
				document.getElementById("capstone" + id).innerHTML = textToDisplay;
			});
	} else {
		document.getElementById("capstone" + id).innerHTML = "";
	}
}
