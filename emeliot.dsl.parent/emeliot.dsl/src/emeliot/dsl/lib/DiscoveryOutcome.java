package emeliot.dsl.lib;

public class DiscoveryOutcome {
	
    private boolean hasError;
    private String outcomeMsg;

    public DiscoveryOutcome() {
        this.hasError = false;
        this.outcomeMsg = "No error found";
    }

    public boolean hasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getOutcomeMsg() {
        return outcomeMsg;
    }

    public void setOutcomeMsg(String outcomeMsg) {
        this.outcomeMsg = outcomeMsg;
    }
}
