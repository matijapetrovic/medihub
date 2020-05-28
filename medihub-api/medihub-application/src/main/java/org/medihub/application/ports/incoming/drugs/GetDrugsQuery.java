package org.medihub.application.ports.incoming.drugs;

import java.util.List;

public interface GetDrugsQuery {
    public List<GetDrugsOutput> getDrugs();
}
