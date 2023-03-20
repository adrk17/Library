Problem współbieżności, czytelnicy i pisarze

1. Wyjaśnienie działania programu, komunkacji między wątkami itp. 

Program w funkcji main tworzy bibliotękę i ustaloną wcześniej liczbę wątków Reader oraz Writer które to w swoich konstruktorach otrzymują referencję do biblioteki.
Wątki są w pętli głównej odpalane jeden po drugim i wchodzą w swoje funkcje run(), w których to określoną liczbę razy będą próbowali wejść do biblioteki i zacząć czytanie/pisanie.

Biblioteka poprzez swoje funkcje enterLibrary() oraz exitLibrary() organizuje dostęp do biblioteki wykorzystując do tego trzy semafory:
 
-queueSemaphore odpowiedzialny jest za ustawienie wątków w kolejce do biblioteki, jest to semafor z 1 permittem do którego każdy wątek próbuje wejść przy wejściu do biblioteki. Jeżeli semafor jest wolny wtedy dany jest dostęp do semafora biblioteki. Po wejściu do biblioteki zwalniany jest semafor kolejki przez co następny wątek otrzymuje dostęp i jest "1. w kolejce do wejścia"

-librarySemaphore jest odpowiedzialny za ilość Readerów oraz Writerów w bibliotece, domyślnie maksymalnie w bibliotece może być 5 Readerów albo 1 Writer który zajmuje całą bibliotekę. Dzieje się to poprzez podanie w argumencie funkcji enterLibrary() i exitLibrary() odpowiedniej liczby zajmowanych permitów, dla Readera jest to 1 a dla Writera domyślnie 5. Po otrzymaniu dostępu poprzez semafor kolejki wątek próbuje dostać się do biblioteki i jeśli jest na tyle miejsca w bibliotece zostaje wpuszczony a jeśli nie czeka aż zwolni się miejsce. Po wejściu do biblioteki zwalniana jest kolejka tak aby następne wątki mogły próbować wejść do biblioteki co zapewnia równe traktowanie wątków.

-mutex jest pomocniczym semaforem który odpowiedzialny jest za zwiększanie i zmniejszanie liczników kolejki i miejsca w bibliotece

Po wejściu do biblioteki następuje czytanie/pisanie które trwa określąną ilość czasu (sleep())
Po tym czasie uruchamiana jest funkcja exitLibrary() która zwalnia zasoby biblioteki tak aby następne wątki mogły do niej wejść

Po wykonaniu powyższych czynności wątek jest ponownie usypiany na jakiś czas po czym na nowo próbuje dostać się do biblioteki, to ile razy wątek się zapętli określone jest przez loopCounter w klasach Reader oraz Writer

2. Sposób uruchomienia programu

w module main, w folderze target znajduje się wykonywalna paczka jar, którą można uruchomić poleceniem java -jar main-1.0-jar-with-dependencies.jar







