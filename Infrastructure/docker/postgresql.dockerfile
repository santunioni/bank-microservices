FROM postgres:12-alpine
USER postgres
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgresql
EXPOSE 5432
