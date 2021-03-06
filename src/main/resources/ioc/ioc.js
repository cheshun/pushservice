var ioc = {
	// 读取配置文件
	config : {
		type : "org.nutz.ioc.impl.PropertiesProxy",
		fields : {
			paths : [ "config.properties" ]
		}
	},
	// 数据源
	dataSource : {
		type : "com.mchange.v2.c3p0.ComboPooledDataSource",
		events : {
			depose : "close"
		},
		fields : {
			driverClass: {
				java : "$config.get('db.driver')"
			},
			jdbcUrl: {
				java : "$config.get('db.url')"
			},
			user: {
				java : "$config.get('db.user')"
			},
			password : {
				java : "$config.get('db.password')"
			}
		}
	},
	// Dao
	dao : {
		type : "org.nutz.dao.impl.NutDao",
		args : [ {
			refer : "dataSource"
		} ]
	},
    //service
    userService: {
        type: "org.cheshun.pushservice.service.UserService",
        fields: {
            dao: { refer: "dao" }
        }
    },
    //Filter
    authFilter : {
        type : "org.cheshun.pushservice.filter.AuthFilter",
        args : [
            { refer: "config"},
            { refer: "dao"}
        ]
    },
    // Action
    universityAction : {
        type : "org.cheshun.pushservice.action.UniversityAction",
        fields : {
            dao: { refer : "dao" }
        }
    }
};