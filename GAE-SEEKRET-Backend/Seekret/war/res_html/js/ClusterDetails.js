
/** Lib that handles the ClusterInforamtion Layout */
/*global document: false */

function ClusterDetails() {
    'use strict';
}

//Add Image 
ClusterDetails.prototype.addElementImage = function (elementForImageId, imageId, url) {
    'use strict';

    var elementForImage, idForImageTag, newImageTag;

    elementForImage = document.getElementById(elementForImageId);
    idForImageTag = imageId;

    this.removeElementById(idForImageTag, elementForImageId);

    newImageTag = document.createElement('img');
    newImageTag.setAttribute('id', idForImageTag);
    newImageTag.setAttribute('src', url);
    elementForImage.appendChild(newImageTag);
};

ClusterDetails.prototype.addElementDiv = function (parentDivId, childDivId, text) {
    'use strict';
    var parentElementForDiv, newImageTag;

    parentElementForDiv = document.getElementById(parentDivId);
    this.removeElementById(childDivId, parentDivId);
    newImageTag = document.createElement('div');
    newImageTag.setAttribute('id', childDivId);
    newImageTag.innerHTML = text;
    parentElementForDiv.appendChild(newImageTag);
};

//Remove element
ClusterDetails.prototype.removeElementById = function (elementToRemoveId, parentElementId) {
    'use strict';
    var parentElement, elementToRemove;
    parentElement = document.getElementById(parentElementId);
    elementToRemove = document.getElementById(elementToRemoveId);
    if (elementToRemove !== null) {
        parentElement.removeChild(elementToRemove);
    }
};