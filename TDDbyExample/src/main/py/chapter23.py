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
        #return self.result

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
    def setUp(self):
        self.result = TestResult()
    def testSetUp(self):
        test=WasRun("")
        test.setUp()
        assert(test.log == "setUp ")
        #print(test.log)
    def testTemplateMethod(self):
        test=WasRun("testMethod")
        test.run(self.result)
        assert(test.log == "setUp testMethod tearDown ")
        #print(test.log)
    def testResult(self):
        test = WasRun("testMethod")
        #locResult = TestResult()
        test.run(self.result)
        assert("1 run, 0 failed" == self.result.summary())
    def testFailedResult(self):
        test = WasRun("brokenMethod")
        #locResult = TestResult() 
        test.run(self.result)
        #print("**"+self.result.summary())
        assert("1 run, 1 failed" == self.result.summary())
    def testSuite(self):
        suite = TestSuite()
        suite.add(WasRun("testMethod"))
        suite.add(WasRun("brokenMethod"))
        #locResult = TestResult()
        suite.run(self.result)
        assert("2 run, 1 failed"==self.result.summary())
    def testFailedResultFormatting(self):
        #result = TestResult()
        self.result.testStarted()
        self.result.testFailed()
        assert("1 run, 1 failed" == self.result.summary())

class TestSuite:
    def __init__(self):
        self.tests = []
    def add(self, test):
        self.tests.append(test)
    def run(self, result):
        for test in self.tests:
            test.run(result)
        return result  
        
class TestResult:
    def __init__(self):
        self.runCount = 0
        self.failureCount = 0
        self.caseName = ""
    def testStarted(self):
        self.runCount = self.runCount + 1    
    def testFailed(self):
        self.failureCount = self.failureCount + 1
    def summary(self):
        return "%d run, %d failed" % (self.runCount, self.failureCount)
    def printSummary(self):
        str = self.summary()
        print("case name: <%s>, %s." % (self.caseName, str))

# print(TestCaseTest("testSetUp").run().summary())
# print(TestCaseTest("testTemplateMethod").run().summary())
# print(TestCaseTest("testResult").run().summary())
# print(TestCaseTest("testFailedResult").run().summary())

# TestCaseTest("testSetUp").run().printSummary()
# TestCaseTest("testTemplateMethod").run().printSummary()
# TestCaseTest("testResult").run().printSummary()
# TestCaseTest("testFailedResult").run().printSummary()
suite = TestSuite()
suite.add(TestCaseTest("testTemplateMethod"))
suite.add(TestCaseTest("testResult"))
suite.add(TestCaseTest("testFailedResultFormatting"))
suite.add(TestCaseTest("testFailedResult"))
suite.add(TestCaseTest("testSuite"))
result = TestResult()
suite.run(result)
result.printSummary()