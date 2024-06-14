FROM node:18-alpine
WORKDIR /app
COPY /src /app/src
COPY /public /app/public
COPY package.json /app
ARG REACT_APP_BACKEND_URL
ENV REACT_APP_BACKEND_URL=${REACT_APP_BACKEND_URL}
RUN npm install
CMD ["npm", "start"]