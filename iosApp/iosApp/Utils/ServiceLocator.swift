//
//  ServiceLocator.swift
//  iosApp
//
//  Created by Matheus Felipe on 29/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation

class ServiceLocator {
    
    static let shared = ServiceLocator()
    
    
    private var services: [String: Any] = [:]
    
    
    private init() {}
    
    
    func register<Service>(_ service: Service, for type: Service.Type) {
        let key = String(describing: type)
        services[key] = service
    }
    
    
    func resolve<Service>(_ type: Service.Type) -> Service {
        let key = String(describing: type)
        return services[key] as! Service
    }
}

