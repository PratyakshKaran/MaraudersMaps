export function exactMatch(r, str) {
    var match = str.match(r);
    return match!=null && str == match[0];
}

export function readBody(request, cb) {
	var fullBody = '';
	request.on('data', function(chunk) {
		fullBody += chunk.toString();
	});
	request.on('end', function() {
		cb(JSON.parse(fullBody));
	});
}