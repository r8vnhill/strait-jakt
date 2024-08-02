import kotlin.jvm.optionals.getOrElse

plugins {
    id("io.github.gradle-nexus.publish-plugin")
}

val versionCatalog: VersionCatalog = versionCatalogs.named("libs")
val jaktVersion: VersionConstraint =
    versionCatalog.findVersion("jakt").getOrElse { error("Version for 'jakt' not found") }

allprojects {
    group = "cll.ravenhill.jakt"
    version = jaktVersion
}

nexusPublishing {
    // Configure maven central repository
    // https://github.com/gradle-nexus/publish-plugin#publishing-to-maven-central-via-sonatype-ossrh
    repositories {
        sonatype {  //only for users registered in Sonatype after 24 Feb 2021
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}
