// Replaces all instances of the given substring.
String.prototype.replaceAll = function(
	strSubString, // The string you want to replace in.
	strTarget // The substring you want to replace
	){
	var strText = this;
	var intIndexOfMatch = strText.indexOf(strTarget);
	 
	// Keep looping while an instance of the target string
	// still exists in the string.
	while (intIndexOfMatch != -1){
	// Relace out the current instance.
	strText = strText.replace(strSubString,strTarget);
	// Get the index of any next matching substring.
	intIndexOfMatch = strText.indexOf(strSubString);
	}
	 
	// Return the updated string with ALL the target strings
	// replaced out with the new substring.
	return(strText);
}

var PopupUtil = {
	
	checkForShow: function(){
		var lastShowPopup = localStorage.getItem('lastShowPopup');
		var restorePopup = localStorage.getItem('restorePopup');
		if (restorePopup==undefined){
			restorePopup = 'false';
			localStorage.setItem('restorePopup', restorePopup);
		}
		var lastShowTitle = localStorage.getItem('lastShowTitle');
		if (lastShowPopup != undefined && restorePopup == 'true' && document.URL.indexOf('/login')<0){
			if (DataSynchronizer.getLoginedUser() == localStorage.getItem('lastShowUser')){
				openPopup(lastShowPopup, lastShowTitle);
			}
		}
	},
	
	updatePopupStatus: function(link, title){
		var lastShowPopup = localStorage.getItem('lastShowPopup');
		var lastShowTitle = localStorage.getItem('lastShowTitle');
		var restorePopup = 'false';
		if (link.indexOf('/login') < 0){
			lastShowPopup = link;
			lastShowTitle = title;
			localStorage.setItem('lastShowPopup', lastShowPopup);
			localStorage.setItem('lastShowTitle', lastShowTitle);
			localStorage.setItem('lastShowUser', DataSynchronizer.getLoginedUser());
		}else{
			if (lastShowPopup != undefined){
				restorePopup = 'true';
			}
		}
		localStorage.setItem('restorePopup', restorePopup);
	},
	
}