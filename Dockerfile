FROM gradle:8.2.1-jdk17

WORKDIR /hexlet-javalin

COPY /hexlet-javalin .

RUN gradle installDist

CMD ./build/install/HexletJavalin/bin/HexletJavalin
