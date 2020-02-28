define(['app'],function(app) {

	function Tsyy(values) {
		values = values || {};
		this.id = values['id'] || Math.floor((Math.random() * 100000) + 5).toString();

		this.firstName = values['firstName'] || '';
		this.lastName = values['lastName'] || '';
		this.phone = values['phone'] || '';
	}

	Tsyy.prototype.setValues = function(formInput) {
		for(var field in formInput){
			if (this[field] !== undefined) {
				this[field] = formInput[field];
			}
		}
	};

	Tsyy.prototype.validate = function() {
		var result = true;
		if (!this.firstName && !this.lastName) {
			result = false;
		}
		return result;
	};

	return Tsyy;
});
