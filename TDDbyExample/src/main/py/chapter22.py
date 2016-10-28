class TestCase:
    def __init__(self, name):
        self.name = name
    def setUp(self):
        pass
    def tearDown(self):
        pass    
    def run(self):
        self.result = TestResult()
        self.result.caseName = self.name
        self.result.testStarted()
        self.setUp()
        try:
            method = getattr(self, self.name)
            method()
        except:
            self.result.testFailed()
        self.tearDown()
        return self.result

class WasRun(TestCase):
    # def __init__(self, name):
    #     TestCase.__init__(self, name)
    def setUp(self):
        self.wasRun = None
        #self.wasSetUp = 1
        self.log ="setUp "
    def tearDown(self):
        self.log = self.log + "tearDown "
    def testMethod(self):
        self.wasRun = 1
        self.log = self.log + "testMethod "
    def brokenMethod(self):
        raise Exception

class TestCaseTest(TestCase):
    def testSetUp(self):
        test=WasRun("")
        test.setUp()
        assert(test.log == "setUp ")
        #print(test.log)
    def testTemplateMethod(self):
        test=WasRun("testMethod")
        test.run()
        assert(test.log == "setUp testMethod tearDown ")
        #print(test.log)
    def testResult(self):
        #print("**"+self.result.summery())
        test  = WasRun("testMethod")
        self.result = test.run()
        assert("1 run, 0 failed" == self.result.summery())
        #print(self.result.summery())
    def testFailedResult(self):
        test = WasRun("brokenMethod")
        self.result = test.run()
        #print("**"+self.result.summery())
        assert("1 run, 1 failed" == self.result.summery())

class TestResult():
    def __init__(self):
        self.runCount = 0
        self.failureCount = 0
        self.caseName = ""
    def testStarted(self):
        self.runCount = self.runCount + 1    
    def testFailed(self):
        self.failureCount = self.failureCount + 1
    def summery(self):
        return "%d run, %d failed" % (self.runCount, self.failureCount)
    def printSummery(self):
        print("case name: <%s>, %s." % (self.caseName, self.summery()))

# print(TestCaseTest("testSetUp").run().summery())
# print(TestCaseTest("testTemplateMethod").run().summery())
# print(TestCaseTest("testResult").run().summery())
# print(TestCaseTest("testFailedResult").run().summery())
TestCaseTest("testSetUp").run().printSummery()
TestCaseTest("testTemplateMethod").run().printSummery()
TestCaseTest("testResult").run().printSummery()
TestCaseTest("testFailedResult").run().printSummery()