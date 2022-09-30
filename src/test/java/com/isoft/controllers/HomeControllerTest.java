package com.isoft.controllers;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HomeControllerTest {

//    private HomeController controller;
//    private MockMvc mockMvc;
//
//    @Autowired
//    public HomeControllerTest(HomeController controller) {
//        this.controller = controller;
//    }
//
//    @BeforeEach
//    void setUp() {
//        mockMvc= MockMvcBuilders.standaloneSetup(controller).build();
//    }
//
//    @Test
//    void showHome() {
//        try {
//            mockMvc.perform(MockMvcRequestBuilders.get("/home"))
//                    .andExpect(view().name("home"))
//                    .andExpect(model().attribute("list",new ArrayList<>(
//                            Collections.singleton(new Student(15,"Ali Mohammad","Zamani",31L)))))
//                    .andReturn();
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}