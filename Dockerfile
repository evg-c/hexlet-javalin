FROM gradle:8.2.1-jdk17

WORKDIR /HelloJavalin

COPY /HelloJavalin .

RUN gradle installDist

CMD ./build/install/HexletJavalin/bin/HexletJavalin
