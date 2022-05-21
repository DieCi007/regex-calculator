echo "Building project..."
./gradlew build --exclude-task test

echo "Building Docker image..."
docker build -t urbi/regex .

echo "Running application..."
docker run -p 8080:8080 urbi/regex
