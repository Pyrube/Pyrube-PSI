/**
 * @(#) Project: Pyrube JSEA
 * 
 * 
 * Website: http://www.pyrube.com
 * Email: customercare@pyrube.com
 * Copyright Pyrube 2009. All rights reserved.
 */

/**
 * JSEA User object
 * 
 * @author Aranjuez
 * @version Dec 01, 2009
 */
window.User = {
	Rules   : {
		data : {
			noteable   : true,
			create     : { noteable : false },
			isInternal : function (data) { return data.userType == 0 || data.userType == 5; } 
		}
	},
	Events  : {
		click : {
			addUserRoles : function () {
				Lookup.open({
					url      : 'admin/role/lookup',
					args     : {
						excludeds : $('#roles').grid().getProperties("roleId")
					},
					complete : function (rs) {
						for (var i = 0; i < rs.length; i++) {
							var rowData = $.extend({}, rs[i]);
							$('#roles').grid().createRow(rowData);
						}
					}
				});
			}
		}
	},
	Methods : {
		grid  : { 
			rights : function (operation) { 
				Lookup.open({
					url      : operation.url,
					urlParams: operation.params,
					args     : {
						roleId : operation.params.roleId
					}
				});
			} 
		}
	}
};