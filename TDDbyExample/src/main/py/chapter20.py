class TestCase:
    def __init__(self, name):
        self.name = name
    def setUp(self):
        pass
    def tearDown(self):
        pass    
    def run(self):
        self.setUp()
        method = getattr(self, self.name)
        method()
        self.tearDown()

class WasRun(TestCase):
    def __init__(self, name):
        TestCase.__init__(self, name)
    def setUp(self):
        self.wasRun = None
        #self.wasSetUp = 1
        self.log ="setUp "
    def testMethod(self):
        self.wasRun = 1
        self.log = self.log + "testMethod "
    def tearDown(self):
        self.log = self.log + "tearDown "
        
class TestCaseTest(TestCase):
    # def testRunning(self):
    #     self.test.run()
    #     assert(self.test.wasRun)
    #     assert(self.test.log =="setUp testMethod ")
    def testTemplateMethod(self):
        test=WasRun("testMethod")
        test.run()
        #assert(self.test.wasSetUp)
        assert(test.log == "setUp testMethod tearDown ")

TestCaseTest("testTemplateMethod").run();
#TestCaseTest("testRunning").run();