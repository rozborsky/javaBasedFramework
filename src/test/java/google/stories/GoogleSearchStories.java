package google.stories;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.util.List;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

public class GoogleSearchStories extends JUnitStories{

    public Configuration configuration() {

        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(this.getClass()));
//                .useStoryReporterBuilder(new StoryReporterBuilder()
//                        .withCodeLocation(codeLocationFromClass(this.getClass()))
//                .withFormats(CONSOLE, TXT, HTML, XML));
    }


    public List<CandidateSteps> candidateSteps() {
        return new InstanceStepsFactory(configuration(),
                new StepsDefinition()).createCandidateSteps();
    }


    protected  List<String> storyPaths() {
        return  new StoryFinder().findPaths(codeLocationFromClass(this.getClass()),
                "**/*.story", "");
    }
}
