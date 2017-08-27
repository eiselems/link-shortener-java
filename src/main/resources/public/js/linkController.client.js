'use strict';

(function () {
   var form = document.querySelector('form');
   var textField = document.querySelector('#linkInput');
   var getLinkBtn = document.querySelector('#getLinkButton');
   var output = document.querySelector('#output');

   var addButton = document.querySelector('.btn-add');
   var deleteButton = document.querySelector('.btn-delete');
   var clickNbr = document.querySelector('#click-nbr');
   var apiUrl = appUrl + '/links/';
   
   var modal = picoModal("Your link was generated <p class='generated' id='generatedLink'></p><button id='copyButton'>Copy to Clipboard</button>");

   //ajaxFunctions.ready(ajaxFunctions.ajaxRequest('GET', apiUrl, updateClickCount));

   /**addButton.addEventListener('click', function () {

      ajaxFunctions.ajaxRequest('POST', apiUrl, function () {
         ajaxFunctions.ajaxRequest('GET', apiUrl, updateClickCount);
      });

   }, false);

   deleteButton.addEventListener('click', function () {

      ajaxFunctions.ajaxRequest('DELETE', apiUrl, function () {
         ajaxFunctions.ajaxRequest('GET', apiUrl, updateClickCount);
      });

   }, false);

**/
   
   function copyToClipboard(){
      var cpBtn = document.querySelector('#copyButton'); 
      var emailLink = document.querySelector('#generatedLink');  
      var range = document.createRange();
      range.selectNode(emailLink);  
      window.getSelection().addRange(range);  
      
      try {  
         // Now that we've selected the anchor text, execute the copy command  
         var successful = document.execCommand('copy');  
         var msg = successful ? 'successful' : 'unsuccessful';  
         console.log('Copy email command was ' + msg);
         cpBtn.innerHTML = "Copied!";
      } catch(err) {  
         console.log('Oops, unable to copy');  
      }  

     // Remove the selections - NOTE: Should use
     // removeRange(range) when it is supported  
     window.getSelection().removeAllRanges(); 
   };

   getLinkInput.addEventListener('click', function (e) {
      e.preventDefault();
      console.log(textField.checkValidity());
      if(!textField.checkValidity()){
         picoModal("Invalid link - it needs to start with http(s).").show();   
      }else{
            let body = new Object();
            body.target = textField.value;
            console.log(body);
            ajaxFunctions.ajaxRequest('POST', apiUrl, function (data){
            console.log(data);
            var json = JSON.parse(data);
            let generatedLink = apiUrl + json.shortLink;
            console.log(modal);
            modal.show();
            document.querySelector('#generatedLink').innerHTML = generatedLink;
            var cpBtn = document.querySelector('#copyButton');
            cpBtn.addEventListener ("click", copyToClipboard, false);
            output.innerHTML = "Last generated link for "+ textField.value +":<br>" + "<span class='generated'>" + generatedLink + "</span>";
      }, JSON.stringify(body));
      }
   }, false);


})();
