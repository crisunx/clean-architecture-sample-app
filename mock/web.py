#!/usr/bin/env python

import re
import sys
import ssl
import random
from http.server import BaseHTTPRequestHandler, HTTPServer
from os import curdir, sep

class HTTPServerRequestHandler(BaseHTTPRequestHandler):
    resp_code = "200" if sys.argv else sys.argv[1]

    def send_content(self):
        try:
            print("Url: " + self.path + " Header app-version: " + self.headers["app-version"])
        except:
            print("Url: " + self.path)

        print("")
        print("------------------")

        id = random.randint(1, 90000)
        text = random.choice(['Ola 123', 'Oi 453', 'Teste 666'])
        message = '{"id": ' + str(id) + ', "text": "' + text + '"}'

        self.send_response(int(HTTPServerRequestHandler.resp_code))
        self.send_header('Content-type', 'text/plain')
        self.end_headers()
        self.wfile.write(bytes(message, "utf8"))

    def do_GET(self):
        HTTPServerRequestHandler.send_content(self)

    def do_PUT(self):
        HTTPServerRequestHandler.send_content(self)

    def do_POST(self):
        HTTPServerRequestHandler.send_content(self)

def run():
    resp_code = "200" if sys.argv else sys.argv[1]

    server_address = ('0.0.0.0', 9991)
    print('Starting http server at 9991 default response code ' + resp_code)
    httpd = HTTPServer(server_address, HTTPServerRequestHandler)

    print('running server...')
    httpd.serve_forever()

run()
