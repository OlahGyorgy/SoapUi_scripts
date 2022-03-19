    import com.eviware.soapui.model.testsuite.*
    import com.eviware.soapui.support.types.StringToObjectMap
    import com.eviware.soapui.SoapUI
    import com.eviware.soapui.settings.SSLSettings

    def replacement = {

            if(it == '?'){'_IS'}
            else if(it == '*'){'X'}
            else if(it == ':'){'-'}
            else if(it == '>'){'_GT_'}
            else if(it == '<'){'_LT_'}
            else if(it == '/'){'_PER_'}
            else if(it == '|'){'_OR'}
            else null
        }
        

    for(project in SoapUI.workspace.projectList) {
            pr_name = project.name.replaceAll('<>',' NOT ')
            pr_name = project.name.collectReplacements(replacement)
            project.setName(pr_name)

            project.getTestSuiteList().each {
                    TestSuite ts = it
                    ts_name = ts.getName()
                    ts_name = ts_name.collectReplacements(replacement)
                    ts.setName(ts_name)
                    List<TestCase> tcs = ts.getTestCaseList()
                    tcs.each(){
                        TestCase tc = it
                        //if(tc.getName()=="001_gen_Time UTC"){tc.setPropertyValue("MyProp","ASD")}
                        tc.getTestStepList().each(){
                            TestStep tsp = it

                            //select RestTestSteps    
                            if(tsp instanceof com.eviware.soapui.impl.wsdl.teststeps.RestTestRequestStep){
                                //Select Tests steps where the enpoint contains "core" or "http" and dom stmg.
                                /*if(tsp.getHttpRequest().getEndpoint().contains("core")||tsp.getHttpRequest().getEndpoint().contains("http") && !tsp.getName().contains("core_")){ //toDO core_core_core why?
                                    String tsp_name = tsp.getName()
                                    //tsp_name = tsp_name.split('_').last()
                                    tsp_name="core_"+tsp_name
                                    tsp.setName(tsp_name)
                                    
                                }
                                }*/


                                  if(tsp.getHttpRequest().getResource().contains("asd")){
                            tc.setPropertyValue("SS.findByStatus","1")
                            log.info "set tc property"
                        }
                                

                        }
                        
                    
                    }
                    
            }
            
        }
    }    