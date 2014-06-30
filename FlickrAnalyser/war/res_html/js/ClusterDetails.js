/**
 * Lib that handles the ClusterInforamtion Layout
 */


function ClusterDetails() {
	//Do nothing
}
//Add Image 
ClusterDetails.prototype.addElementImage = function(elementForImageId,imageId, url) {
	var elementForImage = document.getElementById(elementForImageId);
	var idForImageTag = imageId;
	
	this.removeElementById(idForImageTag, elementForImageId);
	
	var newImageTag = document.createElement('img');
	newImageTag.setAttribute('id', idForImageTag);
	newImageTag.setAttribute('src', url);
	elementForImage.appendChild(newImageTag);
	
	
	
};

//Remove element
ClusterDetails.prototype.removeElementById = function (elementToRemoveId,parentElementId) {
	var parentElement = document.getElementById(parentElementId);
	var elementToRemove = document.getElementById(elementToRemoveId);
	if (elementToRemove != null) {
		parentElement.removeChild(elementToRemove);
	}
};

	

 
