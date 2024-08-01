FROM gradle:8.2.1-jdk17

WORKDIR /HexletJavalin

COPY /HexletJavalin .

RUN gradle installDist

CMD ./build/install/HexletJavalin/bin/HexletJavalin
