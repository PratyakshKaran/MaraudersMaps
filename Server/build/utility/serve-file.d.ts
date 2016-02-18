/// <reference path="../../typings/tsd.d.ts" />
export declare function sendFile(fileName: any, range: any, response: any): void;
export declare function sendCompleteFile(fileName: any, response: any): void;
export declare function sendPartialFile(fileName: any, range: any, response: any): void;
export declare function sendResponse(response: any, status: any, responseHeaders: any, readable: any): void;
export declare function getMimeFromFileName(fileName: any): any;
export declare function getExtFromFileName(fileName: any): any;
export declare function checkIfValidFile(fileName: any): boolean;
