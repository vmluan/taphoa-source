//Dashboard move attributes
var dashboardMoveDistance = undefined;
var dashboardMoveSpeed = 500;
var dashboardMoveDuration = 2000;
var lockAnimate = false;
var c = new CorvusHelper(); 

function CorvusHelper() { 
	this.menu = new MenuHelper() ; 
	this.section = new SectionHelper() ; 
	this.company = new Company();
}

function Company() { 
	this.id;
    this.contact;
    this.dateApproved;
    this.dateRegistered;
    this.approvalComment;
    this.dateRejected;
    this.rejectionComment;
    this.surveyorFor;
    this.name;
    this.shortName;
    this.telephoneNumber;
    this.addressLine1;
    this.addressLine2;
    this.taxNumber;
    this.companyNumber;
}

function Contact() { 
	this.firstName;
    this.openId;
    this.roles;
    this.operationalEntity;
    this.confirmationID;
    this.lastName;
    this.altEmail;
    this.notificationAddress;
    this.telNumber;
    this.userName;
}

function Concession() { 
    this.name;
    this.description;
    this.location;
    this.area;
    this.taxValue;
    this.openingDate;
    this.startDate;
    this.endDate;
    this.concessionHolder;
    this.surveyCompany;
    this.issueDate;
    this.awardComment;
}
function Location() {
	this.id;
} 

function MenuHelper() { 
	/**
	 * Currently we have 5 categories. 
	 */
	this.categories = [1,2,3,4,5];
	/**
	 * Currently we have 8 sub-categories. 
	 */
	this.subCategories = [1,2,3,4,5,6,7,8,9,10];
	/** 
	 * Filters the dashboard to just those tiles belonging to the menu's 
	 * category.
	 */
	this.highlightCategory = function(sectId) {
		var categoryToHighlight = $('#'+sectId).data('category');
		$('.itemBlockElement[data-category='+categoryToHighlight+']').show("slide", {'duration': dashboardMoveDuration}, dashboardMoveSpeed);
		for (var category in this.categories) {
			if (category != categoryToHighlight) { 
				$('.itemBlockElement[data-category='+category+']').hide("slide", {'duration': dashboardMoveDuration}, dashboardMoveSpeed);
			}
		}
	}
	this.highlightSubCategory = function(sectId) {
		$('.categoryContainer').css('left', '0px');	
		var categoryToHighlight = $('#'+sectId).data('sub-category');
		$('.itemBlockElement[data-sub-category='+categoryToHighlight+']').show("slide", {'duration': dashboardMoveDuration}, dashboardMoveSpeed);
//		for (var category in this.subCategories) {
//			if (category != categoryToHighlight) { 
//				$('.itemBlockElement[data-sub-category='+category+']').hide("slide", {'duration': dashboardMoveDuration}, dashboardMoveSpeed);
//			}
//		}
		for (var category = 0; category <= this.subCategories.length; category++) {
			if (category != categoryToHighlight) {
				$('.itemBlockElement[data-sub-category='+category+']').hide("slide", {'duration': dashboardMoveDuration}, dashboardMoveSpeed);
			}
		}
	}
	this.removeHighlight = function() { 
		$('.categoryContainer').css('left', '0px');	
		//for (var category in this.subCategories) {
		for (var category = 0; category <= this.subCategories.length; category++) {
			$('.itemBlockElement[data-sub-category='+category+']').show("slide", {'duration': dashboardMoveDuration}, dashboardMoveSpeed);
		}
	}
	
}

/**
 * Collects together helper functions for sections (aka panels).
 */
function SectionHelper() { 
	
	/**
	 * Used by panel.tagx to toggle visibility of a section element.  
	 * @param sectId The id of the section element. Also relies on conventions for the structure of the section. 
	 */
	this.toggleState = function(sectId) {
		if ($('#_content_'+sectId+'_id').css('display') == 'none') { 
			/* show content */
	        $('#_content_'+sectId+'_id').css('display','block');   
	        $('#_title_'+sectId+'_id .ui-icon-triangle-1-e').removeClass('ui-icon-triangle-1-e').addClass('ui-icon-triangle-1-s');
		} else { 
			/* hide content */
	        $('#_content_'+sectId+'_id').css('display','none');
	        $('#_title_'+sectId+'_id .ui-icon-triangle-1-s').addClass('ui-icon-triangle-1-e').removeClass('ui-icon-triangle-1-s');
		}
	}
} 

/**
 * Display scroll buttons when mouse over to dashboard bar
 */
function dashboardBarMouseOver(dashboadBar) {
	var elementBlock = jQuery(dashboadBar).find('.categoryContainer');
	var scrollable = (elementBlock.position().left < elementBlock.parent().position().left);
	var parent = elementBlock.parent();
	var listChild = elementBlock.find('div.itemBlockElement:visible');
	var childLength = listChild.length;
	var lastChild = jQuery(listChild[childLength-1]);
	if (lastChild != undefined && lastChild.position() != undefined && !scrollable) {
		var elementLeft = elementBlock.position().left;
		scrollable = scrollable
				|| ((elementLeft + lastChild.position().left + lastChild.width()) > (parent.position().left + parent
						.width()));
	}
	if (scrollable) {
		jQuery(dashboadBar).find('.scrollButton').show();
	} else {
		jQuery(dashboadBar).find('.scrollButton').hide();
	}
}

/**
 * Hide scroll buttons when mouse out dashboard bar
 */
function dashboardBarMouseOut(dashboadBar) {
	jQuery(dashboadBar).find('.scrollButton').hide();
}

function calMoveDistance(button) {
	var element = jQuery(button).parent().parent().find('.itemBlockElement');
	if (element != undefined) {
		var result = (element.width() + parseInt(element.css('marginRight').replace('px', '')));
		return result + "px";
	} else {
		return '235px';
	}
}

/**
 * Scroll dashboard bar when click left arrow
 */
function dashboardLeftArrowClick(button) {
	if (dashboardMoveDistance == undefined) {
		dashboardMoveDistance = calMoveDistance(button);
	}
	var dashboadBar = jQuery(button).parent().parent();
	var elementBlock = dashboadBar.find('.categoryContainer');
	var scrollable = (elementBlock.position().left < elementBlock.parent().position().left);
	if (scrollable && (!lockAnimate)) {
		lockAnimate = true;
		elementBlock.animate({
			'left' : '+=' + dashboardMoveDistance
		}, dashboardMoveSpeed, function() {
			lockAnimate = false;
		});
	}
}

/**
 * Scroll dashboard bar when click right arrow
 */
function dashboardRightArrowClick(button) {
	if (dashboardMoveDistance == undefined) {
		dashboardMoveDistance = calMoveDistance(button);
	}
	var dashboadBar = jQuery(button).parent().parent();
	var elementBlock = dashboadBar.find('.categoryContainer');
	try {
		var parent = elementBlock.parent();
		var listChild = elementBlock.find('div.itemBlockElement:visible');
		var childLength = listChild.length;
		var lastChild = jQuery(listChild[childLength-1]);
		var scrollable = ((elementBlock.position().left + lastChild.position().left + lastChild.width()) > (parent
				.position().left + parent.width()));
		if (scrollable && (!lockAnimate)) {
			lockAnimate = true;
			elementBlock.animate({
				'left' : '-=' + dashboardMoveDistance
			}, dashboardMoveSpeed, function() {
				lockAnimate = false;
			});
		}
	} catch (e) {
		console.log(e);
	}
}

// Key action
var ctrl_down = false;
var ctrl_key = 17;
var space_key = 32;
var backspace_key = 8;

// Record ctrl key
jQuery(document).keydown(function(e) {
	if (e.keyCode == ctrl_key) {
		ctrl_down = true;
	}
}).keyup(function(e) {
	if (e.keyCode == ctrl_key) {
		ctrl_down = false;
	}
});

// Key down action
jQuery(document).keydown(function(e) {
	if (ctrl_down && (e.keyCode == space_key)) {
		jQuery('.headerSearchField').focus();
		e.preventDefault();
		return false;
	}

	// Menu keyboard shortcuts
	var categoryElementArray = jQuery('ul.menuBlock > li > div.category');
	if (ctrl_down && e.keyCode >= 49 && e.keyCode < categoryElementArray.length + 49) {
		var menuCategory = jQuery(categoryElementArray[e.keyCode - 49]);
		menuCategory.click();
		e.preventDefault();
		return false;
	}

	// Reset dashboard shortcut
	if (ctrl_down && e.keyCode == 48) {
		c.menu.removeHighlight();
		e.preventDefault();
		return false;
	}
	
	if (ctrl_down && (e.keyCode == backspace_key)) {
		window.parent.location = "/";
		e.preventDefault();
		return false;
	}
});

// Window resize listener
jQuery(window).bind("resize", function() {
	resizeElementToMatchBrowserSize();
});

/**
 * Resize menu and dashboard to match browser size
 */
function resizeElementToMatchBrowserSize() {
	// Menu scroll
	var menu = jQuery('#menu');
	jQuery('#menu > ul').css('width', menu.width() - 15);
	menu.css('height', jQuery(window).height() - jQuery('#footer').height() - jQuery('.headerWraper').height() - 15
			- 80);
	menu.mouseover(function() {
		menu.css('overflow-y', 'auto');
	});
	menu.mouseout(function() {
		menu.css('overflow-y', 'hidden');
	});
	// Main div scroll
	var main = jQuery('#main');
	if (document.getElementById('menu') != undefined) {
		main.css('marginRight', '20px');
		jQuery('#main > div').css('width', main.width() - 15);
	}
	main.css('height', jQuery(window).height() - jQuery('#footer').height() - jQuery('.headerWraper').height() - 15
			- 80);
	main.mouseover(function() {
		main.css('overflow-y', 'auto');
	});
	main.mouseout(function() {
		main.css('overflow-y', 'hidden');
	});
	//Header message
	updateHeaderMessageLocation();
}

function updateHeaderMessageLocation(){
	var headerElement = $('#header');
	var headerMessageElement = headerElement.find('.c-message-area');
	headerMessageElement.css('top', headerElement.height()/2 - headerMessageElement.height()/2);
}

/**
 * Parse and find element in html text
 */
function parseHTML(html, idStr) {
	var root = document.createElement("div");
	root.innerHTML = html;
	return findElement(root, idStr);
}

/**
 * Find element with id is elementId is child of parent element
 */
function findElement(parent, elementId) {
	var allChilds = parent.childNodes;
	for ( var i = 0; i < allChilds.length; i++) {
		var child = allChilds[i];
		if (child.id == elementId) {
			return child;
		} else {
			var temp = findElement(child, elementId);
			if (temp != null) {
				return temp;
			}
		}
	}
	return null;
}

/**
 * Submit ajax url with parameters receive from listElements and refresh
 * elements with id in listelementId.
 */
function submitAjaxGetReplaceListElement(url, listelementId, listElements, actionAfterSucess) {
	url = buildLink(url, listElements);
	jQuery.ajax({
		type : "GET",
		url : url,
		dataType : "html",
		success : function(data) {
			for ( var int = 0; int < listelementId.length; int++) {
				var elementId = listelementId[int];
				refreshElement(data, elementId);
			}
			if (actionAfterSucess != undefined) {
				actionAfterSucess();
			}
		}
	});
}

/**
 * Submit ajax url with parameters receive from listElements and refresh
 * elements with id in listelementId.
 */
function submitAjaxPostReplaceListElement(url, listelementId, listElements, actionAfterSucess) {
	var data = {};
	for ( var i = 0; i < listElements.length; i++) {
		var element = listElements[i];
		data[element.name] = element.value;
	}
	// Submit ajax
	$.ajax({
		type : "POST",
		url : url,
		data : data,
		// Success handle
		success : function(data) {
			// Refresh elements
			for ( var int = 0; int < listelementId.length; int++) {
				var elementId = listelementId[int];
				fetchDataToElement(elementId, data);
			}
			// Callback
			if (actionAfterSucess != undefined) {
				actionAfterSucess();
			}
		}
	});
}

/**
 * Submit ajax url with parameters receive from listElements and refresh
 * elements with id in listelementId. If scrollElementId not empty, scroll to
 * element with id scrollElementId.
 */
function submitAjaxGetReplaceListElementWithDifferentelement(url, dataElementId, listelementId, listElements,
		actionAfterSucess) {
	url = buildLink(url, listElements);
	jQuery.ajax({
		type : "GET",
		url : url,
		dataType : "html",
		success : function(data) {
			for ( var int = 0; int < listelementId.length; int++) {
				var elementId = listelementId[int];
				refreshElementWithDifferentElement(data, dataElementId, elementId);
			}
			if (actionAfterSucess != undefined) {
				actionAfterSucess();
			}
		}
	});
}

/**
 * Build url with origin url and list param element
 */
function buildLink(url, listElements) {
	for ( var i = 0; i < listElements.length; i++) {
		var element = listElements[i];
		if (url.indexOf("?") == -1) {
			url = url + "?";
		} else {
			url = url + "&";
		}
		url = url + element.name + "=" + element.value;
	}
	return url;
}

/**
 * Refresh element with new html data
 */
function refreshElement(data, elementId) {
	var gotcha = parseHTML(data, elementId);
	if (gotcha == undefined) {
		jQuery('#' + elementId).html("");
	} else {
		jQuery('#' + elementId).html(gotcha.innerHTML);
	}
}

/**
 * Refresh element with new html data
 */
function refreshElementWithDifferentElement(data, dataElementId, elementId) {
	var gotcha = parseHTML(data, dataElementId);
	if (gotcha == undefined) {
		jQuery('#' + elementId).html("");
	} else {
		jQuery('#' + elementId).html(gotcha.innerHTML);
	}
}


function getelementArrayIds(idArrayString) {
	var temp = idArrayString.split(",");
	var result = new Array();
	var arrayIndex = 0;
	for ( var int = 0; int < temp.length; int++) {
		var element = temp[int];
		result[arrayIndex] = jQuery.trim(element);
		arrayIndex++;
	}
	return result;
}

var isPopupActive = false;
$(function() {
   
    $.address.change(function(e) {
	   if (isPopupActive == true) {
		$.address.value("/");
		isPopupActive = false;
		closePopup(); 
	}
	
    });
 });
function openPopup(link, title) {
	// Show popup
	PopupUtil.updatePopupStatus(link,title);
	var windowHeight = jQuery(window).height();
	var windowWidth = jQuery(window).width();
	var formWidth = windowWidth * 0.6; 
	$('#dialog').dialog({
		width: formWidth,
		height: windowHeight,
	    autoOpen: true,
	    position: 'right',
	    resizable: false,
	    title: title,
	    show: { effect: 'drop', direction:"right" },
		hide: { effect:'drop', direction:"right"}
  	});
	// Loading image position
	// Add loading image
	var loadingImg = jQuery('<img style="position: relative;" width="60px" height="60px"/>');
	loadingImg.attr('src', 'resources/images/loading.gif');
	var popupContent = jQuery('#dialog');
	popupContent.html(loadingImg);
	loadingImg.css('left', popupContent.width() / 2 - loadingImg.width() / 2);
	loadingImg.css('top', $('#dialog').height()/2);
	$('#dialog').attr('currentLink', link);
	if(isPopupActive == false) {
		$.address.value(link);
		isPopupActive = true;
	}
	submitAjaxGetReplaceListElementWithDifferentelement(link, "main", getelementArrayIds('dialog'), new Array(), window.clearPopupLinkAndForm);
}

function closePopup() {
	try{
		$('#dialog').dialog('close');
	}catch (e) {
		//console.log(e);
	}
}

function submitFormUsingAjax(formId, sucessHanlder){
	var form = $('#' + formId)[0];
	var formData = new FormData(form);
	$.ajax({
		url: $(form).attr('action'),
		data: formData,
		type: $(form).attr('method'),
		success: function(data){
			sucessHanlder(data);
		},
		processData: false,
		contentType: false,
	});
}

function clearPopupLinkAndForm(){
	//Change all link inside popup to ajax
	var listLink = $('#dialog').find('a');
	for (var i=0; i<listLink.length; i++){
		var linkElement = $(listLink[i]);
		var alink = linkElement.attr('href');
		if (alink!=undefined && alink!='#' && alink.indexOf('http://')!=0 && alink.indexOf('https://')!=0 && alink!='javascript:;'){
			if (alink.indexOf('?')==0 || jQuery.trim(alink)==''){
				var currentLink = $('#dialog').attr('currentLink');
				if (currentLink.indexOf('?')>0){
					var temp = currentLink.substring(0, currentLink.indexOf('?'));
					currentLink = temp;
				}
				var temp = currentLink + alink;
				alink = temp;
			}
			linkElement.attr('href', 'javascript:;');
			linkElement.attr('popupLink', alink);
			linkElement.bind('click',function(){
				var ajaxLink = $(this).attr('popupLink');
				openPopup(ajaxLink, $('.ui-dialog').find('.ui-dialog-title').html());
			});
		}
	}
	// Check timeout
	var loginPanel =  $('#dialog').find('#login');
	if (loginPanel.length > 0){
		localStorage.setItem('restorePopup', 'true');
	}else{
		localStorage.setItem('restorePopup', 'false');
	}
	// TODO Change all submit button inside popup
}

function getParent(element, tagName){
	var jQueryElement = $(element);
	var parentElement = jQueryElement;
	while (parentElement.prop("tagName").toLowerCase()!=tagName){
		var temp = parentElement.parent();
		parentElement = temp;
	}
	return parentElement;
}

function logout(){
	if (localStorage.getItem('offline') == 'true') {
		DataSynchronizer.doLogout();
	}
	var loginedUser = DataSynchronizer.getLoginedUser();
	DataSynchronizer.checkServicesStatus(function(){
		doOnlineLogout();
	}, function(){
		window.location = '/login';
	});
}

function doOnlineLogout(){
	localStorage.removeItem('lastShowPopup');
	localStorage.removeItem('lastShowTitle');
	localStorage.setItem('restorePopup', 'false');
	DataSynchronizer.clearOfflineData();
	checkConnectImageAndTooltip();
	window.location='/resources/j_spring_security_logout';
}

function getParameterByName(name) {
	name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
	var regexS = "[\\?&]" + name + "=([^&#]*)";
	var regex = new RegExp(regexS);
	var results = regex.exec(window.location.search);
	if(results == null) {
		return "";
	} else {
		return decodeURIComponent(results[1].replace(/\+/g, " "));
	}
}

function globalSearchShow(event){
	// Get elements
	var header = $('#header');
	var target = $(event.currentTarget);
	var searchBar = $('#c-global-search-box').parent();
	var isVisible = searchBar.is(':visible');
	// Hide other popup
	var listPopup = $('.c-global-popup');
	for ( var int = 0; int < listPopup.length; int++) {
		var popup =  $(listPopup[int]);
		if (searchBar.attr('id')!=popup.attr('id')){
			popup.hide();
		}
	}
	// Check status
	if (isVisible==false){
		searchBar.css('right', $(window).width() - target.position().left - target.width());
		searchBar.css('top', header.position().top + header.height());
		searchBar.show('blind', {}, 150);
	}else{
		searchBar.hide('blind', {}, 150);
	}
}

function toolbarSettingshow(event){
	// Get elements
	var header = $('#header');
	var target = $(event.currentTarget);
	var settingPopup = $('#toolbarSettingBox');
	var isVisible = settingPopup.is(':visible');
	// Hide other popup
	var listPopup = $('.c-global-popup');
	for ( var int = 0; int < listPopup.length; int++) {
		var popup = $(listPopup[int]);
		if (settingPopup.attr('id')!=popup.attr('id')){
			popup.hide();
		}
	}
	// Check status
	if (isVisible == false){
		settingPopup.css('left', target.position().left);
		settingPopup.css('top', header.position().top + header.height());
		settingPopup.show('blind', {}, 150);
	}else{
		settingPopup.hide('blind', {}, 150);
	}
}

function submitByAjax(button, renderId, successCallback){
	var parent = $(button).parent();
	
    while(parent.prop('tagName')!='FORM'){
       parent = parent.parent();
    }
    var formId = parent.attr('id');
	//var form = $('#' + formId)[0];
    var form = document.getElementById(formId);
	var formData = new FormData(form);
	var id = '';
	$.ajax({
		url: $(form).attr('action'),
		data: formData,
		type: $(form).attr('method'),
		success: function(data){
			if (renderId.trim() == ''){
				renderId = 'dialog';
				id = 'main';
			} else {
				id = renderId;
			}
			var gotcha = parseHTML(data, id);
			if (gotcha == undefined) {
				jQuery('#' + renderId).html("");
			} else {
				jQuery('#' + renderId).html(gotcha.innerHTML);
			}
			clearPopupLinkAndForm();
			if (successCallback!=undefined){
				successCallback();
			}
		},
		processData: false,
		contentType: false,
	});	
}

function invalidateOpenIdSession(type, completeCallback){
	if (type == 'google'){
		invalidateGoogleSession(completeCallback);
	}
}

function invalidateGoogleSession(completeCallback){
	var logoutIFrame = $('#googleLogoutIFrame');
	if (logoutIFrame.length==0){
		logoutIFrame = $(document.createElement('iframe'));
		logoutIFrame.attr('id', 'googleLogoutIFrame');
		logoutIFrame.addClass('logoutIFrame');
		logoutIFrame.appendTo($('body'));
		logoutIFrame.hide();
	}
	var action = function(){
		logoutIFrame.hide();
		if (completeCallback!=undefined){
			completeCallback();
		}
	};
	logoutIFrame.bind('load', action);
	logoutIFrame.bind('error', action);
	logoutIFrame.show();
	logoutIFrame.attr('src', 'https://www.google.com/accounts/Logout');
}

function hideAllPopup(){
	closePopup();
	$('.c-global-popup').hide();
}

function dashboardLoad() {
	if (DataSynchronizer.checkOfflineSession() == false) {
		$.ajax({
			type : "GET",
			url : 'dashboard',
			dataType : "html",
			success : function(data) {
				var bodyElement = $('body');
				bodyElement.empty();
				var temp = data.substring(data.indexOf('<body'), data.indexOf('</body>') + '</body>'.length);
				bodyElement.html(temp);
				dashboardLoadFinished();
			}
		});
	}
}

function dashboardLoadFinished() {
	// Margin for middle element
	var marginTop = jQuery('#header').height() + 20;
	marginTop += jQuery('div.headerWraper > div > div.welcomeBlock > div.welcomeElement').height();
	marginTop += jQuery('.headerShadow').height();
	// Wrapper possition
	jQuery('#wrapper').css('marginTop', marginTop);
	// Menu item
	var subcategoryElementArray = jQuery('ul.menuBlock > li > ul.subCategoryBlock > li.subcategoryElement');
	for ( var int = 0; int < subcategoryElementArray.length; int++) {
		var subCategoryElement = jQuery(subcategoryElementArray[int]);
		var totalWidth = subCategoryElement.height();
		var linkElement = jQuery(subCategoryElement.find('a'));
		var linkWidth = linkElement.height();
		linkElement.css('top', totalWidth / 2 - linkWidth / 2);
	}
	// Upper case for menu
	var categoryElementArray = jQuery('ul.menuBlock > li > div.category > div.titleElement > div.categoryText');
	for ( var int = 0; int < categoryElementArray.length; int++) {
		var categoryElement = jQuery(categoryElementArray[int]);
		var content = categoryElement.html().toUpperCase();
		categoryElement.html(content);
	}
	// Dashboard title
	var titleArrays = jQuery('div.dashboardWrapper > div.dashboardBar > .title');
	for ( var int = 0; int < titleArrays.length; int++) {
		var titleElement = jQuery(titleArrays[int]);
		var totalWidth = titleElement.width();
		var titleText = titleElement.find('label');
		var elementWidth = titleText.width();
		var resultWidth = totalWidth / 2 - elementWidth / 2;
		titleText.css('left', resultWidth);
	}
	// Header toolbar
	var toolbar = $('.c-header-toolbar');
	var header = $('#header');
	toolbar.css('marginTop', header.height() / 2 - toolbar.height() / 2);
	var toolbarSeperator = $('.toolbarSeperator');
	toolbarSeperator.height(header.height() - 10);
	var userSession = toolbar.find(".c-user-session");
	userSession.css('marginTop', toolbar.height() / 2 - userSession.height() / 2);

	// Resize elements
	resizeElementToMatchBrowserSize();

	// truncate description bar
	var descriptionArr = $('.dashboardBar').find('.itemBlockElement').find('.description');
	for ( var i = 0; i < descriptionArr.length; i++) {
		var element = descriptionArr[i].innerHTML;
		var textLength = element.length;
		if (textLength > 76) {
			element = element.substring(0, 76);
			element = element.trim() + "...";
		}
		descriptionArr[i].innerHTML = element;
	}

	// Check popup display
	var canShowPopup = true;
	if ($('#login').length>0 || $('#loginOffline').length>0){
		canShowPopup = false;
	}
	if (canShowPopup==true){
		PopupUtil.checkForShow();
	}
}

function getListElementIdArrays(listElement) {
	var elementReplacedArray = listElement.split(',');
	var result = new Array();
	for ( var i = 0; i < elementReplacedArray.length; i++) {
		var elementId = elementReplacedArray[i];
		result[i] = jQuery.trim(elementId);
	}
	return result;
}

function getListElementArrays(listElement) {
	var elementReplacedArray = getListElementIdArrays(listElement);
	var result = new Array();
	for ( var i = 0; i < elementReplacedArray.length; i++) {
		var elementId = elementReplacedArray[i];
		result[i] = document.getElementById(elementId);
	}
	return result;
}

/**
 * Fetch loaded html data to element content by id
 */
function fetchDataToElement(elementId, data) {
	var gotcha = parseHTML(data, elementId);
	if (gotcha == undefined) {
		$('#' + elementId).html("");
	} else {
		$('#' + elementId).html(gotcha.innerHTML);
	}
}