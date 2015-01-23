function disableAllTheseDays(date) {
	var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();

	
	if (date.getDay() === 6) {
		return [ false, '' ]
	}
	if (date.getDay() === 0) {
		return [ false, '' ]
	}
	for (i = 0; i < urlaubstage.length; i++) {
		if ($.inArray((m + 1) + '-' + d + '-' + y, urlaubstage) != -1) {
			return [ false, 'markOrange' ];
		} else if ($.inArray((m + 1) + '-' + d + '-' + y, dates) != -1) {
			return [ false, '' ]
		}
	}
	return [ true, '' ];
}

PrimeFaces.locales['pt'] = {
	closeText : 'Schließen',
	prevText : 'Voriger',
	nextText : 'Nächster',
	currentText : 'Início',
	monthNames : [ 'Januar', 'Februar', 'März', 'April', 'Mai', 'Juni', 'Juli',
			'August', 'September', 'Oktober', 'November', 'Dezember' ],
	monthNamesShort : [ 'Jan', 'Feb', 'Mär', 'Apr', 'Mai', 'Jun', 'Jul', 'Aug',
			'Sep', 'Okt', 'Nov', 'Dez' ],
	dayNames : [ 'Sonntag', 'Montag', 'Dienstag', 'Mittwoch', 'Donnerstag',
			'Freitag', 'Samstag' ],
	dayNamesShort : [ 'So', 'Mo', 'Di', 'Mi', 'Do', 'Fr', 'Sa' ],
	dayNamesMin : [ 'So', 'Mo', 'Di', 'Mi', 'Do', 'Fr', 'Sa' ],
	weekHeader : 'Woche',
	firstDay : 1,
	isRTL : false,
	showMonthAfterYear : false,
	yearSuffix : '',
	timeOnlyTitle : 'Só Horas',
	timeText : 'Tempo',
	hourText : 'Hora',
	minuteText : 'Minuto',
	secondText : 'Segundo',
	ampm : false,
	month : 'Mês',
	week : 'Woche',
	day : 'Dia',
	allDayText : 'Dia completo'
};