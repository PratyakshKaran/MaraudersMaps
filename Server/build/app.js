/// <reference path="../typings/tsd.d.ts" />
var http = require('http');
var url = require('url');
var sf = require('./utility/serve-file');
var port = 5681;
http.createServer(function (request, response) {
    var uri = url.parse(request.url).pathname;
    uri = decodeURIComponent(uri);
    uri = uri.slice(1, uri.length);
    console.log(uri);
    try {
        if (uri == '') {
            sf.sendCompleteFile("app.js", response);
        }
        else
            response.end('404 Not Found');
    }
    catch (ex) {
        console.log(ex);
        response.end();
    }
}).listen(port);
console.log("Torrent server running at\n  => http://localhost:" + port + "/\nCTRL + C to shutdown");
///////////////////// http server helpers /////////////////
function exactMatch(r, str) {
    var match = str.match(r);
    return match != null && str == match[0];
}
function readBody(request, cb) {
    var fullBody = '';
    request.on('data', function (chunk) {
        fullBody += chunk.toString();
    });
    request.on('end', function () {
        cb(JSON.parse(fullBody));
    });
}
////////////////////////////////////////////////////////// 
