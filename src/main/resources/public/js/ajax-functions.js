'use strict';

var appUrl = window.location.origin;
var ajaxFunctions = {
   ready: function ready (fn) {
      if (typeof fn !== 'function') {
         return;
      }

      if (document.readyState === 'complete') {
         return fn();
      }

      document.addEventListener('DOMContentLoaded', fn, false);
   },
   ajaxRequest: function ajaxRequest (method, url, callback, body) {
      var xhr = new XMLHttpRequest();

      xhr.onreadystatechange = function () {
         if (xhr.readyState === 4 && xhr.status === 201) {
            callback(xhr.response);
         }
      };

      xhr.open(method, url, true);
      xhr.setRequestHeader("Content-Type","application/json");
      xhr.send(body);
   }
};