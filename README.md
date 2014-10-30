9toppiksNew
===========
Before installing Yeoman, you will need the following:
•	Node.js v0.10.x+
•	npm (which comes bundled with Node) v1.4.3+
•	git

for checking the version:
$ node --version && npm --version

If you need to upgrade or install Node, the easiest way is to use an installer for your platform. Download the .msi for Windows or .pkg for Mac from the NodeJS website.

$ git --version

Install the Yeoman toolset
$ npm install --global yo bower grunt-cli

Confirm installation
$ yo --version && bower --version && grunt --version

Running the above should output three separate version numbers:
•	Yeoman
•	Bower
•	Grunt CLI (the command-line interface for Grunt)

Install an AngularJS generator

$ npm install --global generator-angular

