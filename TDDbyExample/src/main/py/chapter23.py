class TestCase:
    def __init__(self, name):
        self.name = name
    def setUp(self):
        pass
    def tearDown(self):
        pass    
    def run(self, result):
        #result = TestResult()
        result.testStarted()
        self.setUp()
        try:
            method = getattr(self, self.name)
            method()
        except:
            result.testFailed()
        self.tearDown()
        #return result

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

class TestCaseTest(TestCase):
    # def testSetUp(self):
    #     test=WasRun("")
    #     test.setUp()
    #     assert(test.log == "setUp ")

    # def testTemplateMethod(self):
    #     test=WasRun("testMethod")
    #     test.run()
    #     assert(test.log == "setUp testMethod tearDown ")

    # def testResult(self):
    #     test  = WasRun("testMethod")
    #     result = test.run()
    #     assert("1 run, 0 failed" == result.summery())

    # def testFailedResult(self):
    #     test = WasRun("brokenMethod")
    #     result = test.run()
    #     assert("1 run, 1 failed" == result.summery())

    def testSuite(self, result):
        suite = TestSuite()
        suite.add(WasRun("testMethod"))
        suite.add(WasRun("brokenMethod"))
        #result = TestResult()
        suite.run(result)
        assert("2 run, 1 failed" == result.summery())

class TestResult():
    def __init__(self):
        self.runCount = 0
        self.failureCount = 0
        #self.caseName = ""
    def testStarted(self):
        self.runCount = self.runCount + 1    
    def testFailed(self):
        self.failureCount = self.failureCount + 1
    def summery(self):
        return "%d run, %d failed" % (self.runCount, self.failureCount)
    #def printSummery(self):
    #    print("case name: <%s>, %s." % (self.caseName, self.summery()))

class TestSuite():
    def __init__(self):
        self.tests = []
    def add(self, test):
        self.tests.append(test)
    def run(self, result):
        for test in tests:
            test.run(result)
        return result        

# print(TestCaseTest("testSetUp").run().summery())
# print(TestCaseTest("testTemplateMethod").run().summery())
# print(TestCaseTest("testResult").run().summery())
# print(TestCaseTest("testFailedResult").run().summery())
# TestCaseTest("testSetUp").run().printSummery()
# TestCaseTest("testTemplateMethod").run().printSummery()
# TestCaseTest("testResult").run().printSummery()
# TestCaseTest("testFailedResult").run().printSummery()

testResult = TestResult()
TestCaseTest("testSuite").run(testResult)
print(testResult.summery())