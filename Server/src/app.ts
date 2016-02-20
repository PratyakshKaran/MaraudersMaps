/// <reference path="../typings/tsd.d.ts" />
import http = require('http');
import url = require('url');
import sf = require('./utility/serve-file');

var port = 5681;

http.createServer(function(request, response) {
    try
    {
        var uri = url.parse(request.url).pathname;
        uri = decodeURIComponent(uri);
        uri = uri.slice(1, uri.length);
        console.log(uri);
        
        if(uri == '') {
            sf.sendCompleteFile("app.js", response);
        }
	    else response.end('404 Not Found');    
    }
	catch(ex) 
    {
		console.log(ex);
        response.end();
	}
}).listen(port);

console.log("Torrent server running at\n  => http://localhost:" + port + "/\nCTRL + C to shutdown");
