run: SongSearcherApp.class TextUITester.class
	java SongSearcherApp

runTests: runDataWranglerTests runBackendDeveloperTests runAlgorithmEngineerTests runFrontendDeveloperTests
	@echo ""

runAlgorithmEngineerTests: AlgorithmEngineerTests.class
	java -jar junit5.jar --class-path . --scan-classpath

AlgorithmEngineerTests.class: AlgorithmEngineerTests.java
	javac -cp .:junit5.jar AlgorithmEngineerTests.java -Xlint

runBackendDeveloperTests: BackendDeveloperTests.class
	java -jar junit5.jar --class-path . --scan-classpath -n BackendDeveloperTests

BackendDeveloperTests.class: BackendDeveloperTests.java SongSearcherBackend.class 
	javac -cp .:junit5.jar BackendDeveloperTests.java -Xlint

SongSearcherBackend.class: SongSearcherBackend.java RedBlackTree.class Song.class
	javac SongSearcherBackend.java

RedBlackTree.class: RedBlackTree.java 
	javac RedBlackTree.java

runDataWranglerTests: DataWranglerTests.class
	java -jar junit5.jar --class-path . --scan-classpath -n DataWranglerTests

DataWranglerTests.class: DataWranglerTests.java
	javac -cp .:junit5.jar DataWranglerTests.java -Xlint

SongLoader.class: SongLoader.java Song.java
	javac SongLoader.java	

Song.class: Song.java
	javac Song.java	

runFrontendDeveloperTests: FrontendDeveloperTests.class SongSearcherFrontend.class SongSearcherBackendFrontendDeveloperPlaceholder.class SongFrontendDeveloperPlaceholder.class
	java -jar junit5.jar --class-path . --scan-classpath -n FrontendDeveloperTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java
	javac -cp .:junit5.jar FrontendDeveloperTests.java -Xlint

SongSearcherBackendPlaceholder.class: SongSearcherBackendPlaceholder.java
	javac SongSearcherBackendPlaceholder.java

SongFrontendDeveloperPlaceholder.class: SongFrontendDeveloperPlaceholder.java
	javac SongFrontendDeveloperPlaceholder.java

TextUITester.class: TextUITester.java
	javac TextUITester.java

SongSearcherApp.class: SongSearcherApp.java SongSearcherFrontend.class SongSearcherBackend.class Song.class
	javac SongSearcherApp.java

SongSearcherFrontend.class: SongSearcherFrontend.java
	javac SongSearcherFrontend.java

clean: 
	rm *.class

