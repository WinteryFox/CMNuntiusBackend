# Nuntius
## About
<a href="https://cm.com"><img align="right" src="https://www.cm.com/cdn/cm/cm.svg" width=27%></a>

Nuntius is a test platform for the messaging API provided by [CM.com](https://cm.com),
being developed by students at [Fontys College of Informatics](https://fontys.nl).



## Getting Started
The following is an instruction on the installation process of this project.
To get a local copy up and running follow these simple example steps.

The project is split in 3 packages
1. MO: Handles incoming messages
2. MT: Handles outgoing messages
3. Lib: Contains various shared functions

### Prerequisites
* Preferred IDE
* Docker
* Postgresql


### Installation
1. Clone the repo
   ```sh
   git clone https://github.com/WinteryFox/CMNuntiusBackend
   ```
2. Install NPM packages
   ```sh
   npm install
   ```
3. Start the project


## Usage
###MO
The mo service exposes a couple of endpoints which are as follows:
* GET /events
  * Returns the current eventstate
* POST /mo
  * Asks server if there are any new messages
* POST /status
  * Asks for the status of a message

###MT
The mt service exposes some endpoints as well these are:
* POST /messages
  * Sends the attached message`.
