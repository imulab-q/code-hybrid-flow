rootProject.name = "code-hybrid-flow"

include("sdk")
findProject(":sdk")?.name = "code-hybrid-flow-sdk"

include("service")
findProject(":service")?.name = "code-hybrid-flow-service"
