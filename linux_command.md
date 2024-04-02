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

