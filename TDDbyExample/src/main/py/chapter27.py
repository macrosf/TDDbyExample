class TestCase:
    def __init__(self, name):
        self.name = name
    def setUp(self):
        pass
    def tearDown(self):
        pass    
    def run(self, result):
        result.caseName = self.name
        result.testStarted()
        self.setUp()
        try:
            method = getattr(self, self.name)
            method()
        except:
            result.testFailed()
        self.tearDown()

class WasRun(TestCase):
    def setUp(self):
        self.wasRun = None
        self.log ="setUp "
    def tearDown(self):
        self.log = self.log + "tearDown "
    def testMethod(self):
        self.wasRun = 1
        self.log = self.log + "testMethod "
    def brokenMethod(self):
        raise Exception

class TestResult:
    def addListener(self):
        pass

# class ResultListener:
#     def __init__(self):
#         self.count=0
#     def startTest(self):
#         self.count = self.count + 1 
            
class ResultListenerTest:
    def testNotification(self):
        self.count = 0
        result = TestResult()
        #listener = ResultListener()
        #result.addListener(listener)
        WasRun("testMethod").run(result)
        assert 1 == listener.count