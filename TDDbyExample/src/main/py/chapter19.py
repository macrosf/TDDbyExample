class TestCase:
    """docstring for TestCase"""
    def __init__(self, name):
        self.name = name
    def setUp(self):
        pass
    def run(self):
        self.setUp()
        method = getattr(self, self.name)
        method()

class WasRun(TestCase):
    def __init__(self, name):
        self.wasRun = None
        TestCase.__init__(self, name)
    def testMethod(self):
        self.wasRun = 1
    def setUp(self):
        self.wasSetUp = 1

class TestCaseTest(TestCase):
    """docstring for TestCaseTest"""
    def testRunning(self):
        test=WasRun("testMethod")
        #print(test.wasRun)
        assert(not test.wasRun)
        test.run()
        #print(test.wasRun)
        assert(test.wasRun)
    def testSetup(self):
        test = WasRun("testMethod")
        test.run()
        assert(test.wasSetUp)

# test=WasRun("testMethod")
# print(test.wasRun)
# test.run()
# print(test.wasRun)
TestCaseTest("testRunning").run();
TestCaseTest("testSetup").run();