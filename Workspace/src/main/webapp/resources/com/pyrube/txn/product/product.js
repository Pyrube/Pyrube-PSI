/**
 * @(#) Project: Pyrube JSEA
 * 
 * 
 * Website: http://www.pyrube.com
 * Email: customercare@pyrube.com
 * Copyright Pyrube 2009. All rights reserved.
 */

/**
 * JSEA Product object
 * 
 * @author Aranjuez
 * @version Dec 01, 2009
 */
window.Product = {
	Rules   : {
		data : {
			noteable   : true,
			create     : { noteable : false },
			transfer   : {
				type : 'text/plain',
				mapping : [
					{name : 'prodNo'},
					{name : 'prodName'},
					{name : 'marketDate', type : 'date', format : 'date'},
					{name : 'costPrice', type : 'money'},
					{name : 'retailPrice', type : 'money'},
					{name : 'promoPrice', type : 'money'}
				]
			}
		}
	},
	Events  : {
		init  : {
			bindClipboard : function () {
				var $products = $('#products').grid();
				$products.regTransfer(Page.DataRule('transfer'));
				document.addEventListener('paste', function (event) {
					$products.imp(event.clipboardData);
				});
			}
		},
		click : {
			clearAllProducts : function () {
				$('#products').grid().removeAll();
			},
			removeSelectedProducts : function () {
				$('#products').grid().removeSection();
			}
		}
	}
};