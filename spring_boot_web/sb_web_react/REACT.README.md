* [Update/or install `nvm`, `node`, `npm`](#updateor-install-nvm-node-npm)
* [Generate React project](#generate-react-project)
* [Maven configuration for Spring Boot Web application with React](#maven-configuration-for-spring-boot-web-application-with-react)

### Update/or install `nvm`, `node`, `npm`

##### `nvm` - [node version manager](https://github.com/nvm-sh/nvm)

* Find the currently available versions: [Releases](https://github.com/nvm-sh/nvm/releases)
* Install or update, for instance for `v0.39.2` version:
  ```bash
  curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.2/install.sh | bash
  source ~/.profile
  source ~/.bashrc
  ```
  **Note:** For update you should not remove the previous version. The script overwrites the existing one.

  The script clones `nvm` repository to `~/.nvm`, and attempts to add the source lines from the snippet below 
  to the correct profile file (`~/.bash_profile`, `~/.zshrc`, `~/.profile`, or `~/.bashrc`).
  
  Nvm installation path variable: `export NVM_DIR="$HOME/.nvm"`
* Check the installed/updated version: 
  ```bash
  nvm --version
  ```
  Output: `0.39.2`

##### `node` installation/update

* Make sure you did not install `nodejs` globally:
  ```bash
  sudo apt-get remove --purge nodejs
  ```
* List of node versions:
  * only LTS versions: 
  ```bash
  nvm ls-remote | grep -i "latest lts"
  ```
  * all available versions: 
  ```bash 
  nvm ls-remote
  ```
* List of installed versions:
  ```bash
  nvm ls
  ```
  Output:
  ```bash
         v12.20.2
  ->     v18.12.1
         v18.17.1
  ```
* Install new specific version:
  ```bash
  nvm install v18.17.1
  ```
  Note: this version is becoming a default one only for the current bash session!
* Switch to one of the previously installed versions:
  ```bash
  nvm use v18.17.1
  ```
* Set permanently default version:
  ```bash
  nvm alias default v18.17.1
  ```
  Note: without this alias, each time after restart or in a new terminal session the previous default version is used
* Uninstall specific version:
  ```bash
  nvm uninstall v12.20.2
  ```
* Check `node` version:
  ```bash
  node --version 
  ```
  
##### `npm` - node package manager

Note: you should avoid manual installation of `npm`.
`npm` gets updated/installed during `nvm` installation/update.
Its version must be aligned with version of `node`.

[Node and npm version compatibility](https://nodejs.org/en/download/releases/)

Check `npm` version:
```bash
npm --version
```

##### Check installed versions of `node`, `npm`, `npx`, `yarn`, and `pnpm`
```bash
npm install check-node-version
npx check-node-version --print
```

### Generate React project

* If you've previously installed `create-react-app` globally via: `$ npm install -g create-react-app`,
  to ensure that `npx` always uses the latest version we recommend you uninstall the package using:
  ```bash
  npm uninstall -g create-react-app
  ```
* Generate project. 

  From within of the project folder (containing `pom.xml` file) run:
  ```bash
  npx create-react-app my-app
  mv my-app frontend
  cd frontend
  npm start
  ```

### Maven configuration for Spring Boot Web application with React

* Check the current versions:
  ```bash
  npx check-node-version --print
  ```
  Output:
  ```text
  pnpm: not found
  yarn: not found
  node: 18.17.1
  npm: 9.6.7
  npx: 9.6.7
  ```
* Check the [last version of `frontend-maven-plugin`](https://mvnrepository.com/artifact/com.github.eirslett/frontend-maven-plugin)
* Check the [last version of `maven-resources-plugin`](https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-resources-plugin)
* Set the corresponding properties
  ```pom.xml
  <properties>
    <!--must start with `v`-->
    <node.version>v18.17.1</node.version> 
    <npm.version>9.6.7</npm.version>

    <!-- https://mvnrepository.com/artifact/com.github.eirslett/frontend-maven-plugin -->
    <frontend-maven-plugin.version>1.13.4</frontend-maven-plugin.version>
    <!-- https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-resources-plugin -->
    <maven-resources-plugin.version>3.3.1</maven-resources-plugin.version>

    <java.version>17</java.version>
  </properties>
  ```
* [Add `frontend-maven-plugin` and `maven-resources-plugin` into `pom.xml`](pom.xml)
* Split frontend building and the whole project building to speed-up dev process.

  To build react project, it takes time. Quite often you change only backend, without touching frontend.
  To achieve it, you can configure `frontend-maven-plugin` to be executed during `install` Maven phase.
 
  As a result with the configured phases:
  * To build both frontend and backend, run: `mvn clean install`
  * To build only backend, without clearing the previous code, run: `mvn package`
  * To build only frontend, you `npm start`
* Run web application as usual: `mvn spring-boot:run`