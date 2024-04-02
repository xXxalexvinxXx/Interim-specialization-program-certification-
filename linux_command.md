1. Используя команду cat в терминале операционной системы Linux, создать два файла Домашние животные(заполнив файл собаками,кошками, хомяками) и Вьючные животные заполнив файл Лошадьми,верблюдамии ослами, а затем объединить их. Просмотреть содержимое созданного файла. Переименовать файл, дав ему новое имя( Друзья человека).

mkdir prog_hw
cd prog_hw
cat << EOF > pets
dogs
cats
hamsters
EOF

cat << EOF > pack_animals
horses
camels
donkeys
EOF

cat pets pack_animals > animals
cat animals
mv animals human_freiends

2. Создать директорию, переместить файл туда.
mkdir new_dir
mv human_frends ./new_dir


3. Подключить дополнительный репозиторий MySQL. Установить любой пакет из этого репозитория.
sudo add-apt-repository 'deb http://repo.mysql.com/apt/ubuntu/ mantic mysql-8.0'
sudo apt-key adv --keyserver keyserver.ubuntu.com --recv-keys B7B3B788A8D3785C
sudo apt update
sudo apt-get install mysql-workbench-community-dbgsym

4. Установить и удалить deb-пакет с помощью dpkg.
sudo dpkg -i 7zip_22.01+dfsg-8_amd64.deb 
sudo dpkg -r (--remove, -p, --purge) 7zip


