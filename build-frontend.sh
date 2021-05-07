# to run the script you need node installed ( brew install node)
# init static folder
mkdir ./backend/src/main/resources/static

# go to frontend
cd ./frontend || exit

# build fronted
npm install
npm run build
cd ..

# copy frontend to server
cp -R ./frontend/build/* ./backend/src/main/resources/static
