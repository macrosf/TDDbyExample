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
        TestCase.__init__(self, name)
    def testMethod(self):
        self.wasRun = 1
    def setUp(self):
        self.wasRun = None
        self.wasSetUp = 1

# class TestCaseTest(TestCase):
#     """docstring for TestCaseTest"""
#     def testRunning(self):
#         test=WasRun("testMethod")
#         #assert(not test.wasRun)
#         test.run()
#         assert(test.wasRun)
#     def testSetup(self):
#         test = WasRun("testMethod")
#         test.run()
#         assert(test.wasSetUp)

class TestCaseTest(TestCase):
    """docstring for TestCaseTest"""
    def testRunning(self):
        #test=WasRun("testMethod")
        #assert(not test.wasRun)
        self.test.run()
        assert(self.test.wasRun)
    def testSetup(self):
        #test = WasRun("testMethod")
        self.test.run()
        assert(self.test.wasSetUp)
    def setUp(self):
        self.test=WasRun("testMethod")

TestCaseTest("testRunning").run();
TestCaseTest("testSetup").run();
print("OK")