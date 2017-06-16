package ae.milch.restclient.ui.source;

import java.util.List;

import ae.milch.restclient.data.Source;

public interface SourceView {
    void onSourcesLoaded(List<Source> sources);
}
